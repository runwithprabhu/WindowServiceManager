# 📦 Inventory Management System

A modern, full-featured inventory management system with MongoDB backend and standalone HTML version.

## ✨ Features

- **Product Management** - Add, edit, delete, search products
- **Category Management** - Organize products by categories
- **Stock Tracking** - Real-time inventory levels with low stock alerts
- **Duplicate Detection** - Prevents duplicate SKUs automatically
- **Excel Export** - Export filtered products with summary
- **Advanced Filtering** - Search, category, price range, stock status, sorting
- **Beautiful UI** - Modern Material Design with responsive layout
- **Two Modes** - Standalone (localStorage) or API-connected (MongoDB)

## 🚀 Quick Start

### Option 1: Standalone Version (No Setup Required)

1. Open `standalone/index.html` in your browser
2. Start managing inventory immediately!

**Perfect for:** Testing, demos, personal use

### Option 2: Full Backend Setup

**Prerequisites:**
- Node.js 16+ (you have v24.13.1 ✅)
- MongoDB (local or Atlas)

**Steps:**

1. **Install MongoDB** (choose one):
   - **MongoDB Atlas** (Cloud - Recommended): See `docs/MONGODB_SETUP.md`
   - **Local MongoDB**: Download from mongodb.com

2. **Configure Backend:**
   ```bash
   cd backend
   cp .env.example .env
   # Edit .env with your MongoDB connection string
   ```

3. **Start Backend:**
   ```bash
   npm start
   ```

4. **Verify:**
   Open http://localhost:3000/health

5. **Use API Version:**
   Open `standalone/index-api.html` in browser

## 📁 Project Structure

```
inventory-management/
├── backend/                    # Node.js + Express + MongoDB
│   ├── src/
│   │   ├── server.js          # Main server
│   │   ├── models/            # Mongoose models
│   │   │   ├── Product.js
│   │   │   └── Category.js
│   │   └── routes/            # API routes
│   │       ├── products.js
│   │       └── categories.js
│   ├── seed.js                # Demo data seeder
│   ├── test-api.js            # API test script
│   └── .env                   # Configuration
│
├── standalone/                 # Ready-to-use HTML apps
│   ├── index.html             # Standalone (localStorage)
│   ├── index-api.html         # API-connected version
│   ├── test-filter.html       # Filter testing
│   └── reset-data.html        # Data reset utility
│
├── docs/                       # Documentation
│   ├── MONGODB_SETUP.md       # MongoDB installation guide
│   └── TROUBLESHOOTING.md     # Common issues & fixes
│
└── README.md                   # This file
```

## 🔌 API Endpoints

### Products
- `GET /api/products` - Get all products
- `GET /api/products/:id` - Get product by ID
- `POST /api/products` - Create product
- `PUT /api/products/:id` - Update product
- `DELETE /api/products/:id` - Delete product

### Categories
- `GET /api/categories` - Get all categories
- `POST /api/categories` - Create category

### Health
- `GET /health` - Server health check

## 🛠️ Tech Stack

**Backend:**
- Node.js + Express
- MongoDB + Mongoose
- CORS enabled
- Input validation

**Frontend:**
- AngularJS 1.8.2
- Bootstrap 5
- Font Awesome icons
- SheetJS (Excel export)

## 💾 Demo Data

Seed the database with 20 sample products:

```bash
cd backend
node seed.js
```

**Categories included:**
- Electronics (5 products)
- Clothing (4 products)
- Food & Beverages (4 products)
- Home & Garden (4 products)
- Sports & Outdoors (3 products)

## 🧪 Testing

**Test API:**
```bash
cd backend
node test-api.js
```

**Manual testing:**
```bash
# Health check
curl http://localhost:3000/health

# Get products
curl http://localhost:3000/api/products

# Create product
curl -X POST http://localhost:3000/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Product","sku":"TEST001","price":99.99,"quantity":10,"category":"Electronics"}'
```

## 🔧 Configuration

**Backend (.env):**
```env
PORT=3000
MONGODB_URI=mongodb://localhost:27017/inventory_db

# For MongoDB Atlas:
# MONGODB_URI=mongodb+srv://user:pass@cluster.mongodb.net/inventory_db
```

## 📊 Features in Detail

### Duplicate Detection
- **SKU validation** - Prevents duplicate SKUs (hard block)
- **Name warning** - Warns about similar product names
- **Real-time feedback** - Instant validation as you type

### Advanced Filtering
- **Search** - Filter by product name or SKU
- **Category** - Filter by category
- **Stock Status** - In Stock / Low Stock / Out of Stock
- **Price Range** - Min/Max price filters
- **Sorting** - Name, Price, Quantity (ascending/descending)

### Excel Export
- **Filtered export** - Exports only filtered products
- **Summary sheet** - Includes inventory statistics
- **Formatted columns** - Auto-sized, professional layout
- **Calculated fields** - Total value, profit margin, stock status

## 🐛 Troubleshooting

### Backend won't start
- Check if MongoDB is running
- Verify `.env` configuration
- Check port 3000 is available

### MongoDB connection error
- Verify MongoDB URI in `.env`
- For Atlas: Check IP whitelist
- For local: Ensure MongoDB service is running

### Filtering not working
- Clear browser cache
- Check browser console for errors
- Verify API connection status

See `docs/TROUBLESHOOTING.md` for more solutions.

## 📝 Development Notes

### AngularJS Scope Best Practice
Filter variables are wrapped in `$scope.filters` object to avoid scope inheritance issues:

```javascript
// ✅ Correct
$scope.filters = {
    searchText: '',
    category: ''
};

// ❌ Avoid
$scope.searchText = '';
$scope.category = '';
```

This ensures two-way binding works correctly with nested directives.

## 🚀 Deployment

**Backend:**
```bash
# Production mode
NODE_ENV=production npm start
```

**Frontend:**
- Upload `standalone/index-api.html` to web server
- Update API URL in the file if needed

## 📚 Documentation

- `docs/MONGODB_SETUP.md` - MongoDB installation (local & Atlas)
- `docs/TROUBLESHOOTING.md` - Common issues and solutions
- `backend/README.md` - Backend API documentation

## 🎯 Roadmap

- [ ] User authentication
- [ ] Multi-warehouse support
- [ ] Barcode scanning
- [ ] Purchase orders
- [ ] Sales tracking
- [ ] Advanced reports
- [ ] Mobile app

## 📄 License

MIT License

## 🤝 Contributing

Contributions welcome! Please feel free to submit a Pull Request.

---

**Version:** 1.0.0  
**Last Updated:** February 2026

**Quick Links:**
- Backend: http://localhost:3000
- API Docs: http://localhost:3000/api
- Standalone App: `standalone/index.html`
- API Version: `standalone/index-api.html`
