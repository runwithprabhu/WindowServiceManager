@echo off
echo ========================================
echo  Inventory Management Backend Server
echo ========================================
echo.

REM Check if .env exists
if not exist .env (
    echo [ERROR] .env file not found!
    echo.
    echo Please create .env file from .env.example
    echo and configure your MongoDB connection.
    echo.
    pause
    exit /b 1
)

echo [INFO] Starting backend server...
echo.
echo Backend will run on: http://localhost:3000
echo Health check: http://localhost:3000/health
echo API endpoint: http://localhost:3000/api
echo.
echo Press Ctrl+C to stop the server
echo.
echo ========================================
echo.

npm start
