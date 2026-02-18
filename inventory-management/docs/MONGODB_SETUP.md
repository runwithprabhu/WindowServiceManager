# MongoDB Setup Guide

Complete guide for setting up MongoDB for the Inventory Management System.

## Choose Your Option

### Option 1: MongoDB Atlas (Cloud - Recommended)

**Pros:**
- ✅ No installation required
- ✅ Free tier available (512MB)
- ✅ Automatic backups
- ✅ Works from anywhere
- ✅ Easy scaling

**Cons:**
- ❌ Requires internet connection
- ❌ Free tier has limitations

**Setup Time:** 5-10 minutes

---

### Option 2: Local MongoDB

**Pros:**
- ✅ Full control
- ✅ Works offline
- ✅ No data limits
- ✅ Faster (no network latency)

**Cons:**
- ❌ Requires installation
- ❌ Manual backups
- ❌ Windows service management

**Setup Time:** 10-15 minutes

---

## Option 1: MongoDB Atlas Setup

### Step 1: Create Account

1. Go to: https://www.mongodb.com/cloud/atlas/register
2. Sign up with email or Google account
3. Verify your email

### Step 2: Create Cluster

1. Click "Build a Database"
2. Choose **FREE** tier (M0)
3. Select cloud provider (AWS recommended)
4. Choose region closest to you
5. Cluster name: `Cluster0` (default is fine)
6. Click "Create Cluster"

**Wait 3-5 minutes** for cluster creation.

### Step 3: Create Database User

1. Click "Database Access" (left sidebar)
2. Click "Add New Database User"
3. Choose "Password" authentication
4. Username: `inventory_user`
5. Password: Generate secure password (save it!)
6. Database User Privileges: "Read and write to any database"
7. Click "Add User"

### Step 4: Whitelist IP Address

1. Click "Network Access" (left sidebar)
2. Click "Add IP Address"
3. For testing: Click "Allow Access from Anywhere" (0.0.0.0/0)
4. For production: Add your specific IP
5. Click "Confirm"

### Step 5: Get Connection String

1. Click "Database" (left sidebar)
2. Click "Connect" on your cluster
3. Choose "Connect your application"
4. Driver: Node.js, Version: 5.5 or later
5. Copy the connection string

**Example:**
```
mongodb+srv://inventory_user:<password>@cluster0.xxxxx.mongodb.net/?retryWrites=true&w=majority
```

### Step 6: Configure Backend

1. Open `backend/.env`
2. Replace `<password>` with your actual password
3. Add database name:

```env
MONGODB_URI=mongodb+srv://inventory_user:YOUR_PASSWORD@cluster0.xxxxx.mongodb.net/inventory_db?retryWrites=true&w=majority
```

### Step 7: Test Connection

```bash
cd backend
npm start
```

Look for:
```
✅ MongoDB Connected: cluster0-shard-00-00.xxxxx.mongodb.net
🚀 Server running on port 3000
```

---

## Option 2: Local MongoDB Setup

### Step 1: Download MongoDB

1. Go to: https://www.mongodb.com/try/download/community
2. Select:
   - Version: Latest (8.0+)
   - Platform: Windows
   - Package: MSI
3. Click "Download"

### Step 2: Install MongoDB

1. Run the downloaded `.msi` file
2. Choose "Complete" installation
3. **Important:** Check "Install MongoDB as a Service"
4. Service Name: `MongoDB`
5. Data Directory: `C:\Program Files\MongoDB\Server\8.2\data`
6. Log Directory: `C:\Program Files\MongoDB\Server\8.2\log`
7. Click "Next" and "Install"

### Step 3: Verify Installation

Open PowerShell:

```powershell
# Check MongoDB version
& "C:\Program Files\MongoDB\Server\8.2\bin\mongod.exe" --version

# Should show: db version v8.2.x
```

### Step 4: Create Data Directory

```powershell
# Create data directory
New-Item -ItemType Directory -Path C:\data\db -Force
```

### Step 5: Start MongoDB Service

**Option A: Windows Service (Recommended)**

```powershell
# Start service
Start-Service MongoDB

# Check status
Get-Service MongoDB

# Should show: Status = Running
```

**Option B: Manual Start**

```powershell
# Start MongoDB manually
& "C:\Program Files\MongoDB\Server\8.2\bin\mongod.exe" --dbpath C:\data\db
```

Keep this window open while using the app.

### Step 6: Configure Backend

Open `backend/.env`:

```env
PORT=3000
MONGODB_URI=mongodb://localhost:27017/inventory_db
```

### Step 7: Test Connection

```bash
cd backend
npm start
```

Look for:
```
✅ MongoDB Connected: localhost
🚀 Server running on port 3000
```

---

## Verify Setup

### Test 1: Health Check

Open browser: http://localhost:3000/health

Expected response:
```json
{
  "status": "OK",
  "timestamp": "2026-02-18T...",
  "uptime": 1.234
}
```

### Test 2: API Test

```bash
cd backend
node test-api.js
```

Expected output:
```
✅ Health check passed
✅ Products API working
✅ Categories API working
All tests passed!
```

### Test 3: Seed Data

```bash
cd backend
node seed.js
```

Expected output:
```
✅ MongoDB Connected
✅ Database cleared
✅ 5 categories created
✅ 20 products created
✅ Seeding completed successfully!
```

---

## MongoDB Tools

### MongoDB Compass (GUI)

**Download:** https://www.mongodb.com/try/download/compass

**Connect:**
- For Atlas: Use connection string from Atlas
- For Local: `mongodb://localhost:27017`

**Features:**
- Visual database explorer
- Query builder
- Index management
- Performance monitoring

### MongoDB Shell (mongosh)

**Install:**
```powershell
# Download from: https://www.mongodb.com/try/download/shell
# Or use with MongoDB installation
```

**Connect:**
```bash
# For local
mongosh

# For Atlas
mongosh "mongodb+srv://cluster0.xxxxx.mongodb.net" --username inventory_user
```

**Useful Commands:**
```javascript
// Show databases
show dbs

// Use database
use inventory_db

// Show collections
show collections

// Find all products
db.products.find()

// Count products
db.products.countDocuments()

// Find by category
db.products.find({category: "Electronics"})

// Delete all products
db.products.deleteMany({})

// Drop database
db.dropDatabase()
```

---

## Troubleshooting

### Atlas Issues

**Problem:** "Authentication failed"
- ✅ Check username/password in connection string
- ✅ Verify user exists in Database Access
- ✅ Check user has correct permissions

**Problem:** "Connection timeout"
- ✅ Check IP whitelist in Network Access
- ✅ Verify internet connection
- ✅ Check firewall settings

**Problem:** "Database not found"
- ✅ Add database name to connection string
- ✅ Format: `...mongodb.net/inventory_db?retryWrites=...`

### Local MongoDB Issues

**Problem:** "Service won't start"
```powershell
# Check if port 27017 is in use
netstat -ano | findstr :27017

# Kill process if needed
taskkill /PID <PID> /F

# Restart service
Restart-Service MongoDB
```

**Problem:** "Data directory not found"
```powershell
# Create directory
New-Item -ItemType Directory -Path C:\data\db -Force

# Or specify custom path
& "C:\Program Files\MongoDB\Server\8.2\bin\mongod.exe" --dbpath "C:\custom\path"
```

**Problem:** "Access denied"
- Run PowerShell as Administrator
- Check folder permissions on C:\data\db

### Backend Connection Issues

**Problem:** "MongooseServerSelectionError"
- ✅ Check MongoDB is running
- ✅ Verify MONGODB_URI in .env
- ✅ Check network connectivity

**Problem:** "ECONNREFUSED"
- ✅ MongoDB service not running
- ✅ Wrong port number
- ✅ Firewall blocking connection

---

## Best Practices

### Security

**Atlas:**
- ✅ Use strong passwords
- ✅ Whitelist specific IPs in production
- ✅ Enable 2FA on MongoDB account
- ✅ Rotate passwords regularly

**Local:**
- ✅ Enable authentication in production
- ✅ Create user accounts with limited permissions
- ✅ Bind to localhost only (not 0.0.0.0)
- ✅ Use firewall rules

### Performance

- ✅ Create indexes on frequently queried fields
- ✅ Use connection pooling
- ✅ Monitor slow queries
- ✅ Regular backups

### Backups

**Atlas:**
- Automatic backups included in free tier
- Configure backup schedule in Atlas UI

**Local:**
```bash
# Backup database
mongodump --db inventory_db --out C:\backups\

# Restore database
mongorestore --db inventory_db C:\backups\inventory_db\
```

---

## Quick Reference

### Connection Strings

**Atlas:**
```
mongodb+srv://user:pass@cluster.mongodb.net/inventory_db?retryWrites=true&w=majority
```

**Local:**
```
mongodb://localhost:27017/inventory_db
```

**Local with Auth:**
```
mongodb://username:password@localhost:27017/inventory_db
```

### Service Commands

```powershell
# Start
Start-Service MongoDB

# Stop
Stop-Service MongoDB

# Restart
Restart-Service MongoDB

# Status
Get-Service MongoDB
```

### Environment Variables

```env
# Required
MONGODB_URI=mongodb://...

# Optional
PORT=3000
NODE_ENV=development
```

---

## Next Steps

1. ✅ MongoDB installed and running
2. ✅ Backend connected successfully
3. ✅ Seed demo data: `node seed.js`
4. ✅ Test API: `node test-api.js`
5. ✅ Open `standalone/index-api.html`
6. ✅ Start building!

---

**Need Help?**
- MongoDB Docs: https://docs.mongodb.com
- Atlas Support: https://support.mongodb.com
- Community: https://community.mongodb.com
