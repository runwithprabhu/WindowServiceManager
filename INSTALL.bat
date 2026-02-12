@echo off
title Installation Check
color 0B

echo ========================================
echo Windows Services ^& Process Manager
echo Installation Check
echo ========================================
echo.

REM Check Java
echo Checking Java installation...
java -version >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo [OK] Java is installed
    java -version 2>&1 | findstr /i "version"
) else (
    echo [MISSING] Java is not installed
    echo.
    echo Please install Java JDK 8 or higher from:
    echo https://www.oracle.com/java/technologies/downloads/
    echo.
    echo After installation, add Java to your PATH:
    echo 1. Search for "Environment Variables" in Windows
    echo 2. Edit "Path" variable
    echo 3. Add Java bin directory (e.g., C:\Program Files\Java\jdk-XX\bin)
)

echo.

REM Check Chrome
echo Checking Chrome installation...
where chrome >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo [OK] Chrome is installed
) else (
    echo [INFO] Chrome not found in PATH
    echo The application will try to launch with default browser
)

echo.

REM Check required files
echo Checking project files...
if exist "backend\ServicesRestAPI.java" (
    echo [OK] Backend files found
) else (
    echo [ERROR] Backend files missing!
)

if exist "web-ui\device-services-ui.html" (
    echo [OK] Web UI files found
) else (
    echo [ERROR] Web UI files missing!
)

echo.
echo ========================================
echo Installation check complete!
echo ========================================
echo.

if %ERRORLEVEL% EQU 0 (
    echo You can now run LAUNCH.bat to start the application
) else (
    echo Please fix the issues above before running
)

echo.
pause
