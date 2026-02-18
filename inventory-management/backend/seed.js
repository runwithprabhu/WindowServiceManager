// Seed script to populate MongoDB with dummy data
const mongoose = require('mongoose');
require('dotenv').config();

// Import models
const Product = require('./src/models/Product');
const Category = require('./src/models/Category');

// Sample categories
const categories = [
    {
        name: 'Electronics',
        description: 'Electronic devices and accessories'
    },
    {
        name: 'Furniture',
        description: 'Office and home furniture'
    },
    {
        name: 'Stationery',
        description: 'Office supplies and stationery items'
    },
    {
        name: 'Computers',
        description: 'Computer hardware and accessories'
    },
    {
        name: 'Accessories',
        description: 'Various accessories and peripherals'
    }
];

// Sample products
const products = [
    {
        name: 'Laptop Dell XPS 15',
        sku: 'ELEC-LAP-001',
        description: 'High-performance laptop with Intel i7, 16GB RAM, 512GB SSD',
        price: 1299.99,
        cost: 950.00,
        quantity: 15,
        minQuantity: 5,
        maxQuantity: 50,
        unit: 'piece',
        status: 'active',
        tags: ['laptop', 'dell', 'premium']
    },
    {
        name: 'Wireless Mouse Logitech MX Master 3',
        sku: 'ELEC-MOU-001',
        description: 'Ergonomic wireless mouse with precision tracking',
        price: 99.99,
        cost: 65.00,
        quantity: 45,
        minQuantity: 10,
        maxQuantity: 100,
        unit: 'piece',
        status: 'active',
        tags: ['mouse', 'logitech', 'wireless']
    },
    {
        name: 'Mechanical Keyboard RGB',
        sku: 'ELEC-KEY-001',
        description: 'RGB mechanical keyboard with Cherry MX switches',
        price: 149.99,
        cost: 95.00,
        quantity: 8,
        minQuantity: 10,
        maxQuantity: 50,
        unit: 'piece',
        status: 'active',
        tags: ['keyboard', 'mechanical', 'rgb']
    },
    {
        name: '27-inch 4K Monitor',
        sku: 'ELEC-MON-001',
        description: 'Ultra HD 4K monitor with HDR support',
        price: 399.99,
        cost: 280.00,
        quantity: 0,
        minQuantity: 5,
        maxQuantity: 30,
        unit: 'piece',
        status: 'active',
        tags: ['monitor', '4k', 'display']
    },
    {
        name: 'Office Desk Chair Ergonomic',
        sku: 'FURN-CHA-001',
        description: 'Comfortable ergonomic office chair with lumbar support',
        price: 299.99,
        cost: 180.00,
        quantity: 20,
        minQuantity: 5,
        maxQuantity: 40,
        unit: 'piece',
        status: 'active',
        tags: ['chair', 'ergonomic', 'office']
    },
    {
        name: 'Standing Desk Adjustable',
        sku: 'FURN-DSK-001',
        description: 'Electric height-adjustable standing desk',
        price: 599.99,
        cost: 380.00,
        quantity: 12,
        minQuantity: 3,
        maxQuantity: 20,
        unit: 'piece',
        status: 'active',
        tags: ['desk', 'standing', 'adjustable']
    },
    {
        name: 'USB-C Hub 7-in-1',
        sku: 'COMP-HUB-001',
        description: '7-port USB-C hub with HDMI, USB 3.0, and SD card reader',
        price: 49.99,
        cost: 25.00,
        quantity: 60,
        minQuantity: 20,
        maxQuantity: 150,
        unit: 'piece',
        status: 'active',
        tags: ['usb', 'hub', 'adapter']
    },
    {
        name: 'Webcam HD 1080p',
        sku: 'ELEC-CAM-001',
        description: 'Full HD webcam with auto-focus and noise reduction',
        price: 79.99,
        cost: 45.00,
        quantity: 25,
        minQuantity: 10,
        maxQuantity: 60,
        unit: 'piece',
        status: 'active',
        tags: ['webcam', 'camera', 'hd']
    },
    {
        name: 'Wireless Headphones Noise Cancelling',
        sku: 'ELEC-HDP-001',
        description: 'Premium wireless headphones with active noise cancellation',
        price: 249.99,
        cost: 150.00,
        quantity: 18,
        minQuantity: 8,
        maxQuantity: 50,
        unit: 'piece',
        status: 'active',
        tags: ['headphones', 'wireless', 'noise-cancelling']
    },
    {
        name: 'External SSD 1TB',
        sku: 'COMP-SSD-001',
        description: 'Portable external SSD with USB 3.2 Gen 2',
        price: 129.99,
        cost: 80.00,
        quantity: 35,
        minQuantity: 15,
        maxQuantity: 80,
        unit: 'piece',
        status: 'active',
        tags: ['ssd', 'storage', 'external']
    },
    {
        name: 'Notebook A4 Ruled 200 Pages',
        sku: 'STAT-NOT-001',
        description: 'Premium quality ruled notebook',
        price: 5.99,
        cost: 2.50,
        quantity: 150,
        minQuantity: 50,
        maxQuantity: 500,
        unit: 'piece',
        status: 'active',
        tags: ['notebook', 'stationery', 'paper']
    },
    {
        name: 'Ballpoint Pen Blue (Pack of 10)',
        sku: 'STAT-PEN-001',
        description: 'Smooth writing ballpoint pens',
        price: 8.99,
        cost: 4.00,
        quantity: 200,
        minQuantity: 100,
        maxQuantity: 1000,
        unit: 'pack',
        status: 'active',
        tags: ['pen', 'stationery', 'writing']
    },
    {
        name: 'Desk Lamp LED',
        sku: 'FURN-LMP-001',
        description: 'Adjustable LED desk lamp with touch control',
        price: 39.99,
        cost: 22.00,
        quantity: 30,
        minQuantity: 10,
        maxQuantity: 60,
        unit: 'piece',
        status: 'active',
        tags: ['lamp', 'led', 'desk']
    },
    {
        name: 'Cable Organizer Set',
        sku: 'ACC-ORG-001',
        description: 'Cable management organizer set with clips and sleeves',
        price: 15.99,
        cost: 8.00,
        quantity: 75,
        minQuantity: 30,
        maxQuantity: 200,
        unit: 'piece',
        status: 'active',
        tags: ['organizer', 'cable', 'accessory']
    },
    {
        name: 'Laptop Stand Aluminum',
        sku: 'ACC-STD-001',
        description: 'Ergonomic aluminum laptop stand with cooling',
        price: 45.99,
        cost: 25.00,
        quantity: 40,
        minQuantity: 15,
        maxQuantity: 80,
        unit: 'piece',
        status: 'active',
        tags: ['stand', 'laptop', 'aluminum']
    },
    {
        name: 'Printer Paper A4 500 Sheets',
        sku: 'STAT-PAP-001',
        description: 'High-quality white printer paper',
        price: 12.99,
        cost: 7.00,
        quantity: 100,
        minQuantity: 40,
        maxQuantity: 300,
        unit: 'pack',
        status: 'active',
        tags: ['paper', 'printer', 'a4']
    },
    {
        name: 'Wireless Charging Pad',
        sku: 'ELEC-CHG-001',
        description: 'Fast wireless charging pad for smartphones',
        price: 29.99,
        cost: 15.00,
        quantity: 55,
        minQuantity: 20,
        maxQuantity: 120,
        unit: 'piece',
        status: 'active',
        tags: ['charger', 'wireless', 'phone']
    },
    {
        name: 'Desk Organizer Wooden',
        sku: 'FURN-ORG-001',
        description: 'Multi-compartment wooden desk organizer',
        price: 34.99,
        cost: 18.00,
        quantity: 28,
        minQuantity: 10,
        maxQuantity: 50,
        unit: 'piece',
        status: 'active',
        tags: ['organizer', 'desk', 'wooden']
    },
    {
        name: 'HDMI Cable 2m 4K',
        sku: 'COMP-CBL-001',
        description: '2-meter HDMI 2.1 cable supporting 4K@120Hz',
        price: 19.99,
        cost: 9.00,
        quantity: 90,
        minQuantity: 40,
        maxQuantity: 200,
        unit: 'piece',
        status: 'active',
        tags: ['cable', 'hdmi', '4k']
    },
    {
        name: 'Whiteboard Markers Set of 4',
        sku: 'STAT-MRK-001',
        description: 'Dry-erase whiteboard markers in assorted colors',
        price: 9.99,
        cost: 4.50,
        quantity: 120,
        minQuantity: 50,
        maxQuantity: 300,
        unit: 'pack',
        status: 'active',
        tags: ['markers', 'whiteboard', 'stationery']
    }
];

// Connect to MongoDB and seed data
async function seedDatabase() {
    try {
        console.log('🔌 Connecting to MongoDB...');
        await mongoose.connect(process.env.MONGODB_URI || 'mongodb://localhost:27017/inventory_db');
        console.log('✅ Connected to MongoDB\n');

        // Clear existing data
        console.log('🗑️  Clearing existing data...');
        await Product.deleteMany({});
        await Category.deleteMany({});
        console.log('✅ Existing data cleared\n');

        // Insert categories
        console.log('📁 Creating categories...');
        const createdCategories = await Category.insertMany(categories);
        console.log(`✅ Created ${createdCategories.length} categories\n`);

        // Map category names to IDs
        const categoryMap = {};
        createdCategories.forEach(cat => {
            categoryMap[cat.name] = cat._id;
        });

        // Assign categories to products
        const productsWithCategories = products.map(product => {
            let categoryName;
            if (product.sku.startsWith('ELEC')) categoryName = 'Electronics';
            else if (product.sku.startsWith('FURN')) categoryName = 'Furniture';
            else if (product.sku.startsWith('STAT')) categoryName = 'Stationery';
            else if (product.sku.startsWith('COMP')) categoryName = 'Computers';
            else if (product.sku.startsWith('ACC')) categoryName = 'Accessories';
            else categoryName = 'Electronics';

            return {
                ...product,
                category: categoryMap[categoryName]
            };
        });

        // Insert products
        console.log('📦 Creating products...');
        const createdProducts = await Product.insertMany(productsWithCategories);
        console.log(`✅ Created ${createdProducts.length} products\n`);

        // Display summary
        console.log('=' .repeat(60));
        console.log('🎉 DATABASE SEEDED SUCCESSFULLY!');
        console.log('=' .repeat(60));
        console.log('\n📊 Summary:');
        console.log(`   Categories: ${createdCategories.length}`);
        console.log(`   Products: ${createdProducts.length}`);
        console.log(`   Total Value: $${createdProducts.reduce((sum, p) => sum + (p.price * p.quantity), 0).toFixed(2)}`);
        console.log(`   Low Stock Items: ${createdProducts.filter(p => p.quantity <= p.minQuantity).length}`);
        console.log(`   Out of Stock: ${createdProducts.filter(p => p.quantity === 0).length}`);
        console.log('\n📋 Sample Products:');
        createdProducts.slice(0, 5).forEach(p => {
            console.log(`   - ${p.name} (${p.sku}): $${p.price} x ${p.quantity} = $${(p.price * p.quantity).toFixed(2)}`);
        });
        console.log(`   ... and ${createdProducts.length - 5} more products`);
        console.log('\n✅ You can now use the inventory management system!');
        console.log('🌐 Open: http://localhost:3000/api/products');
        console.log('🖥️  Or open: standalone/index-api.html in your browser\n');

    } catch (error) {
        console.error('❌ Error seeding database:', error);
        process.exit(1);
    } finally {
        await mongoose.connection.close();
        console.log('🔌 Database connection closed');
        process.exit(0);
    }
}

// Run the seed function
seedDatabase();
