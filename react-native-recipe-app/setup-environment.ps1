# React Native Environment Setup Script
# Run this script to configure Android SDK environment variables

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  React Native Environment Setup" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Set ANDROID_HOME
Write-Host "Setting ANDROID_HOME..." -ForegroundColor Yellow
$androidSdkPath = "$env:LOCALAPPDATA\Android\Sdk"

if (Test-Path $androidSdkPath) {
    [System.Environment]::SetEnvironmentVariable('ANDROID_HOME', $androidSdkPath, 'User')
    $env:ANDROID_HOME = $androidSdkPath
    Write-Host "✅ ANDROID_HOME set to: $androidSdkPath" -ForegroundColor Green
} else {
    Write-Host "❌ Android SDK not found at: $androidSdkPath" -ForegroundColor Red
    Write-Host "Please install Android Studio first" -ForegroundColor Red
    exit 1
}

Write-Host ""

# Add Android SDK to PATH
Write-Host "Adding Android SDK to PATH..." -ForegroundColor Yellow

$currentPath = [System.Environment]::GetEnvironmentVariable('Path', 'User')

$androidPaths = @(
    "$androidSdkPath\platform-tools",
    "$androidSdkPath\emulator",
    "$androidSdkPath\tools",
    "$androidSdkPath\tools\bin"
)

$pathsAdded = 0
foreach ($path in $androidPaths) {
    if (Test-Path $path) {
        if ($currentPath -notlike "*$path*") {
            $currentPath += ";$path"
            $pathsAdded++
            Write-Host "  Added: $path" -ForegroundColor Gray
        } else {
            Write-Host "  Already in PATH: $path" -ForegroundColor Gray
        }
    } else {
        Write-Host "  ⚠️  Path not found: $path" -ForegroundColor Yellow
    }
}

if ($pathsAdded -gt 0) {
    [System.Environment]::SetEnvironmentVariable('Path', $currentPath, 'User')
    Write-Host "✅ $pathsAdded path(s) added to PATH" -ForegroundColor Green
} else {
    Write-Host "✅ All paths already in PATH" -ForegroundColor Green
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Setup Complete!" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "⚠️  IMPORTANT: Please restart PowerShell for changes to take effect" -ForegroundColor Yellow
Write-Host ""

Write-Host "Next steps:" -ForegroundColor Cyan
Write-Host "1. Close and reopen PowerShell" -ForegroundColor White
Write-Host "2. Verify setup: adb version" -ForegroundColor White
Write-Host "3. Start emulator or connect device" -ForegroundColor White
Write-Host "4. Run: npm run android" -ForegroundColor White
Write-Host ""

Write-Host "Verification commands:" -ForegroundColor Cyan
Write-Host "  echo `$env:ANDROID_HOME" -ForegroundColor Gray
Write-Host "  adb version" -ForegroundColor Gray
Write-Host "  emulator -list-avds" -ForegroundColor Gray
Write-Host ""

# Create local.properties for Android
Write-Host "Creating android/local.properties..." -ForegroundColor Yellow
$localPropertiesPath = "android\local.properties"
$localPropertiesContent = "sdk.dir=$($androidSdkPath -replace '\\', '\\')"

if (Test-Path "android") {
    Set-Content -Path $localPropertiesPath -Value $localPropertiesContent
    Write-Host "✅ Created: $localPropertiesPath" -ForegroundColor Green
} else {
    Write-Host "⚠️  android folder not found, skipping local.properties" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Setup script completed!" -ForegroundColor Green
