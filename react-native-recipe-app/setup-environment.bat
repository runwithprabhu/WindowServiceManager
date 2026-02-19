@echo off
echo ========================================
echo   React Native Environment Setup
echo ========================================
echo.

echo Setting ANDROID_HOME...
setx ANDROID_HOME "%LOCALAPPDATA%\Android\Sdk"
echo ANDROID_HOME set to: %LOCALAPPDATA%\Android\Sdk
echo.

echo Adding Android SDK to PATH...
setx PATH "%PATH%;%LOCALAPPDATA%\Android\Sdk\platform-tools;%LOCALAPPDATA%\Android\Sdk\emulator;%LOCALAPPDATA%\Android\Sdk\tools;%LOCALAPPDATA%\Android\Sdk\tools\bin"
echo Android SDK paths added to PATH
echo.

echo Creating android\local.properties...
if exist android (
    echo sdk.dir=%LOCALAPPDATA%\Android\Sdk > android\local.properties
    echo Created: android\local.properties
) else (
    echo Warning: android folder not found
)
echo.

echo ========================================
echo   Setup Complete!
echo ========================================
echo.
echo IMPORTANT: Please restart your command prompt/PowerShell
echo.
echo Next steps:
echo 1. Close and reopen PowerShell
echo 2. Verify: adb version
echo 3. Start emulator
echo 4. Run: npm run android
echo.
pause
