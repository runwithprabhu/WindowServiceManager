@echo off
cls
echo ========================================
echo  Inventory Management - Backend Server
echo ========================================
echo.

REM Check if .env exists
if not exist backend\.env (
    echo [ERROR] .env file not found!
    echo.
    echo Creating .env file from template...
    copy backend\.env.example backend\.env
    echo.
    echo [INFO] .env file created!
    echo [ACTION REQUIRED] Please edit backend\.env and add your MongoDB connection string
    echo.
    echo For MongoDB Atlas: See MONGODB_ATLAS_QUICK_SETUP.md
    echo For Local MongoDB: Use mongodb://localhost:27017/inventory_db
    echo.
    pause
    exit /b 1
)

echo [INFO] Checking Node.js...
node --version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Node.js is not installed or not in PATH
    echo Please install Node.js from https://nodejs.org/
    pause
    exit /b 1
)

echo [OK] Node.js found
echo.

echo [INFO] Starting backend server...
echo.
echo Backend URL: http://localhost:3000
echo Health Check: http://localhost:3000/health
echo API Endpoint: http://localhost:3000/api
echo.
echo [TIP] Open index-api.html in your browser to use the app
echo [TIP] Press Ctrl+C to stop the server
echo.
echo ========================================
echo.

cd backend
npm start
