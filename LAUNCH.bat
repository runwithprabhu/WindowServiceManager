@echo off
title Windows Services & Process Manager
color 0A

echo ========================================
echo Windows Services ^& Process Manager
echo Portable Edition
echo ========================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Java is not installed or not in PATH
    echo.
    echo Please install Java JDK 8 or higher from:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    pause
    exit /b 1
)

echo [1/3] Compiling REST API Server...
cd backend
javac ServicesRestAPI.java
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed!
    cd ..
    pause
    exit /b 1
)

echo [2/3] Starting REST API Server...
start "REST API Server" cmd /k "java ServicesRestAPI"

REM Wait for server to start
timeout /t 3 /nobreak >nul

echo [3/3] Launching Web UIs...
cd ..

REM Get current directory
set "CURRENT_DIR=%CD%"

REM Launch Device Services UI
start chrome "file:///%CURRENT_DIR:\=/%/web-ui/device-services-ui.html"

REM Wait a moment
timeout /t 1 /nobreak >nul

REM Launch Process Manager UI
start chrome "file:///%CURRENT_DIR:\=/%/web-ui/process-manager-ui.html"

echo.
echo ========================================
echo Application Started Successfully!
echo ========================================
echo.
echo REST API Server: http://localhost:8080
echo.
echo Web UIs opened in Chrome:
echo - Device Services Manager
echo - Process Manager
echo.
echo Press any key to stop the server and exit...
pause >nul

REM Kill Java processes
taskkill /F /IM java.exe >nul 2>&1

echo.
echo Server stopped. Goodbye!
timeout /t 2 /nobreak >nul
