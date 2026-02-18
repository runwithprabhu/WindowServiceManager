# Troubleshooting Guide

Common issues and solutions for the Inventory Management System.

## Backend Issues

### MongoDB Connection Errors

**Error:** `MongooseServerSelectionError: connect ECONNREFUSED`

**Cause:** MongoDB is not running

**Solutions:**
```powershell
# Check if MongoDB service is running
Get-Service MongoDB

# Start MongoDB service
Start-Service MongoDB

# Or start manually
& "C:\Program Files\MongoDB\Server\8.2\bin\mongod.exe" --dbpath C:\data\db
```

---

**Error:** `Authentication failed`

**Cause:** Wrong username/password in connection string

**Solutions:**
1. Check `.env` file for correct credentials
2. For Atlas: Verify user in Database Access
3. Ensure password doesn't contain special characters (URL encode if needed)
4. Test connection string in MongoDB Compass

---

**Error:** `Connection timeout`

**Cause:** Network or firewall blocking connection

**Solutions:**
1. For Atlas: Check IP whitelist in Network Access
2. Verify internet connection
3. Check Windows Firewall settings
4. Try different network (mobile hotspot)

---

### Port Already in Use

**Error:** `Port 3000 already in use`

**Solutions:**
```powershell
# Find process using port 3000
netstat -ano | findstr :3000

# Kill the process (replace <PID> with actual PID)
taskkill /PID <PID> /F

# Or change port in .env
PORT=3001
```

---

### Module Not Found

**Error:** `Cannot find module 'express'`

**Cause:** Dependencies not installed

**Solution:**
```bash
cd backend
npm install
```

---

### Environment Variables Not Loading

**Error:** `MONGODB_URI is undefined`

**Cause:** `.env` file missing or not loaded

**Solutions:**
1. Check if `.env` file exists in `backend/` folder
2. Copy from template: `cp .env.example .env`
3. Verify `dotenv` is installed: `npm install dotenv`
4. Check `.env` syntax (no spaces around `=`)

---

## Frontend Issues

### Filtering Not Working

**Symptom:** Search and category filters don't update product list

**Cause:** AngularJS scope binding issue

**Solution:**
- Already fixed in latest version
- Clear browser cache: Ctrl+Shift+Delete
- Hard refresh: Ctrl+F5
- Check browser console for errors

---

### API Not Connected

**Symptom:** Red "API Disconnected" badge

**Causes & Solutions:**

1. **Backend not running**
   ```bash
   cd backend
   npm start
   ```

2. **Wrong API URL**
   - Check `index-api.html` line ~470
   - Should be: `$scope.API_URL = 'http://localhost:3000/api';`

3. **CORS error**
   - Backend should have CORS enabled (already configured)
   - Check browser console for CORS errors

4. **Firewall blocking**
   - Allow Node.js through Windows Firewall
   - Try accessing http://localhost:3000/health directly

---

### Products Not Loading

**Symptom:** Spinner keeps spinning, no products shown

**Solutions:**

1. **Check API connection**
   - Open http://localhost:3000/health
   - Should return `{"status":"OK"}`

2. **Check browser console**
   - Press F12 → Console tab
   - Look for error messages

3. **Verify data exists**
   ```bash
   cd backend
   node seed.js  # Add demo data
   ```

4. **Test API directly**
   - Open http://localhost:3000/api/products
   - Should return JSON array

---

### Duplicate SKU Warning Won't Clear

**Symptom:** Warning persists even after changing SKU

**Solution:**
- Click outside the input field
- Wait for validation to complete
- Refresh the page if needed

---

### Excel Export Not Working

**Error:** `XLSX is not defined`

**Cause:** SheetJS library not loaded

**Solution:**
- Check internet connection (CDN required)
- Or download SheetJS locally:
  ```html
  <script src="path/to/xlsx.full.min.js"></script>
  ```

---

## Data Issues

### Categories Showing as Objects

**Symptom:** Category displays as `[object Object]`

**Cause:** Old data format

**Solution:**
- Already fixed in latest version
- Clear database and reseed:
  ```javascript
  // In mongosh
  use inventory_db
  db.products.deleteMany({})
  db.categories.deleteMany({})
  ```
  ```bash
  # Then reseed
  node seed.js
  ```

---

### Duplicate Products

**Symptom:** Same product appears multiple times

**Cause:** Multiple seeding or SKU validation disabled

**Solution:**
```javascript
// In mongosh
use inventory_db

// Find duplicates
db.products.aggregate([
  {$group: {_id: "$sku", count: {$sum: 1}}},
  {$match: {count: {$gt: 1}}}
])

// Remove duplicates (keep first)
db.products.aggregate([
  {$group: {_id: "$sku", ids: {$push: "$_id"}}},
  {$match: {ids: {$size: {$gt: 1}}}}
]).forEach(doc => {
  doc.ids.shift(); // Keep first
  db.products.deleteMany({_id: {$in: doc.ids}});
});
```

---

### Lost Data After Refresh

**Symptom:** Data disappears after browser refresh

**Cause:** Using standalone version (localStorage)

**Solution:**
- Use `index-api.html` instead of `index.html`
- Ensure backend is running
- Data persists in MongoDB

---

## Performance Issues

### Slow Loading

**Causes & Solutions:**

1. **Too many products**
   - Add pagination (future feature)
   - Use filters to reduce results
   - Index database fields

2. **Network latency (Atlas)**
   - Choose region closer to you
   - Consider local MongoDB for development

3. **Browser performance**
   - Close other tabs
   - Clear browser cache
   - Update browser

---

### High Memory Usage

**Cause:** Large dataset in memory

**Solutions:**
- Implement pagination
- Use virtual scrolling
- Limit results per page
- Close unused browser tabs

---

## Development Issues

### Changes Not Reflecting

**Solutions:**

1. **Backend changes**
   ```bash
   # Restart server
   Ctrl+C
   npm start
   ```

2. **Frontend changes**
   - Hard refresh: Ctrl+F5
   - Clear cache: Ctrl+Shift+Delete
   - Disable cache in DevTools (F12 → Network → Disable cache)

3. **Database changes**
   ```bash
   # Reseed data
   node seed.js
   ```

---

### npm Commands Not Working

**Error:** `'npm' is not recognized`

**Solution:**
```powershell
# Use full path
& "C:\Program Files\nodejs\npm.cmd" install

# Or add to PATH
$env:Path += ";C:\Program Files\nodejs"
```

---

### Node Version Issues

**Error:** `Unsupported engine`

**Solution:**
```bash
# Check Node version
node --version

# Should be 16+ (you have 24.13.1 ✅)

# Update if needed from: https://nodejs.org
```

---

## Testing Issues

### Test Script Fails

**Error:** `Connection refused` in test-api.js

**Solutions:**
1. Start backend first: `npm start`
2. Wait for "Server running" message
3. Then run: `node test-api.js`

---

### Seed Script Fails

**Error:** `Duplicate key error`

**Cause:** Data already exists

**Solution:**
```bash
# Clear database first
# In mongosh:
use inventory_db
db.products.deleteMany({})
db.categories.deleteMany({})

# Then reseed
node seed.js
```

---

## Browser-Specific Issues

### Chrome

**Issue:** CORS errors
- Check backend CORS configuration
- Try incognito mode
- Disable extensions

### Firefox

**Issue:** localStorage not working
- Check privacy settings
- Allow cookies and site data

### Edge

**Issue:** Compatibility mode
- Ensure modern Edge (Chromium-based)
- Check compatibility settings

---

## MongoDB Compass Issues

### Can't Connect

**Solutions:**

1. **Local MongoDB**
   - Connection string: `mongodb://localhost:27017`
   - Ensure MongoDB service is running

2. **Atlas**
   - Use connection string from Atlas
   - Check IP whitelist
   - Verify credentials

---

### Can't See Database

**Cause:** Database created but empty

**Solution:**
- Database appears after first document is inserted
- Run seed script: `node seed.js`
- Refresh Compass

---

## Common Error Messages

### `EADDRINUSE`
**Meaning:** Port already in use  
**Fix:** Kill process or change port

### `ECONNREFUSED`
**Meaning:** Can't connect to MongoDB  
**Fix:** Start MongoDB service

### `ValidationError`
**Meaning:** Invalid data format  
**Fix:** Check required fields and data types

### `CastError`
**Meaning:** Wrong data type  
**Fix:** Ensure correct types (string, number, ObjectId)

### `MongoServerError: E11000`
**Meaning:** Duplicate key (unique constraint)  
**Fix:** Change SKU or remove duplicate

---

## Getting Help

### Check Logs

**Backend logs:**
- Console where `npm start` is running
- Look for error stack traces

**Frontend logs:**
- Browser console (F12)
- Network tab for API errors

### Debug Mode

**Enable verbose logging:**
```javascript
// In server.js
mongoose.set('debug', true);

// In index-api.html
console.log('Debug:', variable);
```

### Useful Commands

```powershell
# Check services
Get-Service MongoDB
Get-Process node

# Check ports
netstat -ano | findstr :3000
netstat -ano | findstr :27017

# Check MongoDB
mongosh
show dbs
use inventory_db
db.products.find()

# Test API
curl http://localhost:3000/health
curl http://localhost:3000/api/products
```

---

## Still Having Issues?

1. ✅ Check all services are running
2. ✅ Verify configuration files
3. ✅ Review error messages carefully
4. ✅ Test each component separately
5. ✅ Clear cache and restart
6. ✅ Check documentation
7. ✅ Search error message online

**Most issues are:**
- MongoDB not running (50%)
- Wrong configuration (30%)
- Port conflicts (10%)
- Cache issues (10%)

---

**Quick Checklist:**

- [ ] MongoDB service running
- [ ] Backend started (`npm start`)
- [ ] `.env` file configured
- [ ] Port 3000 available
- [ ] Internet connection (for Atlas)
- [ ] Browser cache cleared
- [ ] No console errors
- [ ] API health check passes

If all checked and still not working, review error messages in console for specific guidance.
