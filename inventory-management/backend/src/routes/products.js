const express = require('express');
const router = express.Router();
const Product = require('../models/Product');
const Category = require('../models/Category');
const { body, validationResult } = require('express-validator');

// Helper function to get or create category
async function getCategoryId(categoryInput) {
    // If it's already an ObjectId, return it
    if (categoryInput && categoryInput.toString().match(/^[0-9a-fA-F]{24}$/)) {
        return categoryInput;
    }
    
    // If it's a string (category name), find or create the category
    if (typeof categoryInput === 'string') {
        let category = await Category.findOne({ 
            name: { $regex: new RegExp(`^${categoryInput}$`, 'i') } 
        });
        
        if (!category) {
            // Create new category
            category = await Category.create({
                name: categoryInput,
                description: `Auto-created category for ${categoryInput}`
            });
        }
        
        return category._id;
    }
    
    throw new Error('Invalid category format');
}

// Get all products
router.get('/', async (req, res) => {
    try {
        const { 
            page = 1, 
            limit = 1000, // Increased default limit for frontend
            search, 
            category, 
            status,
            sortBy = 'createdAt',
            order = 'desc'
        } = req.query;

        const query = {};
        
        if (search) {
            query.$text = { $search: search };
        }
        
        if (category) {
            query.category = category;
        }
        
        if (status) {
            query.status = status;
        }

        const products = await Product.find(query)
            .populate('category', 'name')
            .populate('supplier', 'name')
            .sort({ [sortBy]: order === 'desc' ? -1 : 1 })
            .limit(limit * 1)
            .skip((page - 1) * limit)
            .exec();

        const count = await Product.countDocuments(query);

        res.json({
            success: true,
            data: products,
            totalPages: Math.ceil(count / limit),
            currentPage: page,
            total: count
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Get product by ID
router.get('/:id', async (req, res) => {
    try {
        const product = await Product.findById(req.params.id)
            .populate('category')
            .populate('supplier');

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        res.json({
            success: true,
            data: product
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Check for duplicate SKU
router.get('/check/sku/:sku', async (req, res) => {
    try {
        const { sku } = req.params;
        const { excludeId } = req.query;
        
        const query = { sku: sku.toUpperCase() };
        if (excludeId) {
            query._id = { $ne: excludeId };
        }
        
        const exists = await Product.findOne(query);
        
        res.json({
            success: true,
            exists: !!exists,
            message: exists ? 'SKU already exists' : 'SKU is available'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Check for duplicate name
router.get('/check/name/:name', async (req, res) => {
    try {
        const { name } = req.params;
        const { excludeId } = req.query;
        
        const query = { name: { $regex: new RegExp(`^${name}$`, 'i') } };
        if (excludeId) {
            query._id = { $ne: excludeId };
        }
        
        const exists = await Product.findOne(query);
        
        res.json({
            success: true,
            exists: !!exists,
            message: exists ? 'Product name already exists' : 'Product name is available',
            product: exists ? { id: exists._id, name: exists.name, sku: exists.sku } : null
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Create product
router.post('/', [
    body('name').notEmpty().withMessage('Name is required'),
    body('sku').notEmpty().withMessage('SKU is required'),
    body('category').notEmpty().withMessage('Category is required'),
    body('price').isNumeric().withMessage('Price must be a number'),
    body('cost').isNumeric().withMessage('Cost must be a number'),
    body('quantity').isNumeric().withMessage('Quantity must be a number')
], async (req, res) => {
    try {
        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            return res.status(400).json({
                success: false,
                errors: errors.array()
            });
        }

        // Check for duplicate SKU
        const existingSKU = await Product.findOne({ sku: req.body.sku.toUpperCase() });
        if (existingSKU) {
            return res.status(400).json({
                success: false,
                message: `Duplicate SKU: A product with SKU "${req.body.sku}" already exists`,
                field: 'sku',
                existingProduct: {
                    id: existingSKU._id,
                    name: existingSKU.name,
                    sku: existingSKU.sku
                }
            });
        }

        // Check for duplicate name (warning only)
        const existingName = await Product.findOne({ 
            name: { $regex: new RegExp(`^${req.body.name}$`, 'i') } 
        });
        
        // Convert category name to ObjectId (or create new category)
        const categoryId = await getCategoryId(req.body.category);
        
        // Create product with category ObjectId
        const productData = {
            ...req.body,
            category: categoryId
        };
        
        const product = await Product.create(productData);
        
        // Populate category for response
        await product.populate('category', 'name');

        res.status(201).json({
            success: true,
            data: product,
            message: 'Product created successfully',
            warning: existingName ? `Note: A product with similar name "${existingName.name}" already exists` : null
        });
    } catch (error) {
        if (error.code === 11000) {
            const field = Object.keys(error.keyPattern)[0];
            return res.status(400).json({
                success: false,
                message: `Duplicate ${field}: This ${field} already exists in the system`,
                field: field
            });
        }
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Update product
router.put('/:id', async (req, res) => {
    try {
        // Check for duplicate SKU (excluding current product)
        if (req.body.sku) {
            const existingSKU = await Product.findOne({ 
                sku: req.body.sku.toUpperCase(),
                _id: { $ne: req.params.id }
            });
            if (existingSKU) {
                return res.status(400).json({
                    success: false,
                    message: `Duplicate SKU: A product with SKU "${req.body.sku}" already exists`,
                    field: 'sku',
                    existingProduct: {
                        id: existingSKU._id,
                        name: existingSKU.name,
                        sku: existingSKU.sku
                    }
                });
            }
        }
        
        // Convert category name to ObjectId if needed
        if (req.body.category) {
            req.body.category = await getCategoryId(req.body.category);
        }

        const product = await Product.findByIdAndUpdate(
            req.params.id,
            req.body,
            { new: true, runValidators: true }
        ).populate('category', 'name');

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        res.json({
            success: true,
            data: product,
            message: 'Product updated successfully'
        });
    } catch (error) {
        if (error.code === 11000) {
            const field = Object.keys(error.keyPattern)[0];
            return res.status(400).json({
                success: false,
                message: `Duplicate ${field}: This ${field} already exists in the system`,
                field: field
            });
        }
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Delete product
router.delete('/:id', async (req, res) => {
    try {
        const product = await Product.findByIdAndDelete(req.params.id);

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found'
            });
        }

        res.json({
            success: true,
            message: 'Product deleted successfully'
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

// Get low stock products
router.get('/alerts/low-stock', async (req, res) => {
    try {
        const products = await Product.find({
            $expr: { $lte: ['$quantity', '$minQuantity'] },
            status: 'active'
        })
        .populate('category', 'name')
        .sort({ quantity: 1 });

        res.json({
            success: true,
            data: products,
            count: products.length
        });
    } catch (error) {
        res.status(500).json({
            success: false,
            message: error.message
        });
    }
});

module.exports = router;
