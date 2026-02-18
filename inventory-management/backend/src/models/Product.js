const mongoose = require('mongoose');

const productSchema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, 'Product name is required'],
        trim: true,
        maxlength: [100, 'Name cannot exceed 100 characters']
    },
    sku: {
        type: String,
        required: [true, 'SKU is required'],
        unique: true,
        trim: true,
        uppercase: true
    },
    description: {
        type: String,
        maxlength: [500, 'Description cannot exceed 500 characters']
    },
    category: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Category',
        required: [true, 'Category is required']
    },
    supplier: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Supplier'
    },
    price: {
        type: Number,
        required: [true, 'Price is required'],
        min: [0, 'Price cannot be negative']
    },
    cost: {
        type: Number,
        required: [true, 'Cost is required'],
        min: [0, 'Cost cannot be negative']
    },
    quantity: {
        type: Number,
        required: [true, 'Quantity is required'],
        min: [0, 'Quantity cannot be negative'],
        default: 0
    },
    minQuantity: {
        type: Number,
        default: 10,
        min: [0, 'Minimum quantity cannot be negative']
    },
    maxQuantity: {
        type: Number,
        default: 1000
    },
    unit: {
        type: String,
        enum: ['piece', 'kg', 'liter', 'meter', 'box', 'pack'],
        default: 'piece'
    },
    barcode: {
        type: String,
        unique: true,
        sparse: true
    },
    image: {
        type: String,
        default: '/uploads/default-product.png'
    },
    status: {
        type: String,
        enum: ['active', 'inactive', 'discontinued'],
        default: 'active'
    },
    tags: [{
        type: String,
        trim: true
    }],
    specifications: {
        type: Map,
        of: String
    },
    createdBy: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    },
    updatedBy: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User'
    }
}, {
    timestamps: true,
    toJSON: { virtuals: true },
    toObject: { virtuals: true }
});

// Virtual for stock status
productSchema.virtual('stockStatus').get(function() {
    if (this.quantity === 0) return 'out_of_stock';
    if (this.quantity <= this.minQuantity) return 'low_stock';
    return 'in_stock';
});

// Virtual for profit margin
productSchema.virtual('profitMargin').get(function() {
    if (this.cost === 0) return 0;
    return ((this.price - this.cost) / this.cost * 100).toFixed(2);
});

// Indexes
productSchema.index({ name: 'text', description: 'text', sku: 'text' });
productSchema.index({ category: 1, status: 1 });
productSchema.index({ quantity: 1 });

module.exports = mongoose.model('Product', productSchema);
