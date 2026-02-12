@echo off
echo ========================================
echo Starting REST API Server
echo ========================================
echo.
echo Compiling Java REST API...
javac ServicesRestAPI.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilation successful!
    echo Starting server...
    echo.
    java ServicesRestAPI
) else (
    echo.
    echo Compilation failed! Please check for errors.
    pause
)
