# 🚀 React Native Environment Setup Guide

## Current Status Check

✅ **Java:** 25.0.2 (Installed)  
✅ **Android SDK:** Found at `C:\Users\p.selvaraj\AppData\Local\Android\Sdk`  
✅ **Android Studio:** Installed  
✅ **Node.js:** v24.13.1  
✅ **npm:** v11.8.0  

❌ **ANDROID_HOME:** Not set  
❌ **Android SDK in PATH:** Not configured  
❌ **React Native CLI:** Not installed globally  

---

## Step-by-Step Setup

### Step 1: Set ANDROID_HOME Environment Variable

**Option A: Using PowerShell (Permanent)**

```powershell
# Set ANDROID_HOME for current user
[System.Environment]::SetEnvironmentVariable('ANDROID_HOME', "$env:LOCALAPPDATA\Android\Sdk", 'User')

# Verify
$env:ANDROID_HOME = [System.Environment]::GetEnvironmentVariable('ANDROID_HOME', 'User')
Write-Host "ANDROID_HOME set to: $env:ANDROID_HOME"
```

**Option B: Using System Settings (GUI)**

1. Press `Win + X` → System
2. Click "Advanced system settings"
3. Click "Environment Variables"
4. Under "User variables", click "New"
5. Variable name: `ANDROID_HOME`
6. Variable value: `C:\Users\p.selvaraj\AppData\Local\Android\Sdk`
7. Click OK

---

### Step 2: Add Android SDK to PATH

**Using PowerShell (Permanent):**

```powershell
# Get current PATH
$currentPath = [System.Environment]::GetEnvironmentVariable('Path', 'User')

# Add Android SDK paths
$androidPaths = @(
    "$env:LOCALAPPDATA\Android\Sdk\platform-tools",
    "$env:LOCALAPPDATA\Android\Sdk\emulator",
    "$env:LOCALAPPDATA\Android\Sdk\tools",
    "$env:LOCALAPPDATA\Android\Sdk\tools\bin"
)

# Add each path if not already present
foreach ($path in $androidPaths) {
    if ($currentPath -notlike "*$path*") {
        $currentPath += ";$path"
    }
}

# Set the new PATH
[System.Environment]::SetEnvironmentVariable('Path', $currentPath, 'User')

Write-Host "Android SDK paths added to PATH"
```

**Or Using System Settings (GUI):**

1. Press `Win + X` → System
2. Click "Advanced system settings"
3. Click "Environment Variables"
4. Under "User variables", select "Path" → Edit
5. Click "New" and add these paths:
   ```
   C:\Users\p.selvaraj\AppData\Local\Android\Sdk\platform-tools
   C:\Users\p.selvaraj\AppData\Local\Android\Sdk\emulator
   C:\Users\p.selvaraj\AppData\Local\Android\Sdk\tools
   C:\Users\p.selvaraj\AppData\Local\Android\Sdk\tools\bin
   ```
6. Click OK on all dialogs

---

### Step 3: Restart PowerShell

**Important:** Close and reopen PowerShell to load new environment variables.

```powershell
# Verify ANDROID_HOME
echo $env:ANDROID_HOME

# Verify adb is accessible
adb version
```

---

### Step 4: Install React Native CLI (Optional but Recommended)

```powershell
& "C:\Program Files\nodejs\npm.cmd" install -g react-native-cli
```

---

### Step 5: Verify Android SDK Components

Open Android Studio and check SDK Manager:

1. Open Android Studio
2. Tools → SDK Manager
3. Ensure these are installed:
   - ✅ Android SDK Platform 34 (or latest)
   - ✅ Android SDK Build-Tools
   - ✅ Android SDK Platform-Tools
   - ✅ Android SDK Tools
   - ✅ Android Emulator
   - ✅ Intel x86 Emulator Accelerator (HAXM)

---

### Step 6: Create/Start Android Emulator

**Option A: Using Android Studio**

1. Tools → Device Manager
2. Create Device (if none exists)
3. Select: Pixel 6
4. System Image: Android 13 (API 33)
5. Click Finish
6. Start the emulator

**Option B: Using Command Line**

```powershell
# List available emulators
emulator -list-avds

# Start emulator (replace with your AVD name)
emulator -avd Pixel_6_API_33
```

---

### Step 7: Run React Native App

**Terminal 1 - Start Metro Bundler:**

```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" start
```

**Terminal 2 - Run Android App:**

```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" run android
```

Or using React Native CLI:

```powershell
npx react-native run-android
```

---

## Quick Setup Script

Run this PowerShell script to set up everything automatically:

```powershell
# Set ANDROID_HOME
[System.Environment]::SetEnvironmentVariable('ANDROID_HOME', "$env:LOCALAPPDATA\Android\Sdk", 'User')
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"

# Add to PATH
$currentPath = [System.Environment]::GetEnvironmentVariable('Path', 'User')
$androidPaths = @(
    "$env:LOCALAPPDATA\Android\Sdk\platform-tools",
    "$env:LOCALAPPDATA\Android\Sdk\emulator",
    "$env:LOCALAPPDATA\Android\Sdk\tools",
    "$env:LOCALAPPDATA\Android\Sdk\tools\bin"
)

foreach ($path in $androidPaths) {
    if ($currentPath -notlike "*$path*") {
        $currentPath += ";$path"
    }
}

[System.Environment]::SetEnvironmentVariable('Path', $currentPath, 'User')

Write-Host "✅ ANDROID_HOME set to: $env:ANDROID_HOME"
Write-Host "✅ Android SDK paths added to PATH"
Write-Host "⚠️  Please restart PowerShell for changes to take effect"
Write-Host ""
Write-Host "Next steps:"
Write-Host "1. Close and reopen PowerShell"
Write-Host "2. Verify: adb version"
Write-Host "3. Start emulator or connect device"
Write-Host "4. Run: npm run android"
```

---

## Verification Checklist

After setup, verify everything:

```powershell
# Check Java
java -version
# Should show: java version "25.0.2"

# Check Node
& "C:\Program Files\nodejs\node.exe" --version
# Should show: v24.13.1

# Check npm
& "C:\Program Files\nodejs\npm.cmd" --version
# Should show: 11.8.0

# Check ANDROID_HOME
echo $env:ANDROID_HOME
# Should show: C:\Users\p.selvaraj\AppData\Local\Android\Sdk

# Check adb
adb version
# Should show: Android Debug Bridge version

# Check emulator
emulator -list-avds
# Should list available emulators

# Check connected devices
adb devices
# Should list connected devices/emulators
```

---

## Troubleshooting

### "adb is not recognized"

**Solution:**
- Restart PowerShell after setting PATH
- Or add manually: `$env:Path += ";$env:LOCALAPPDATA\Android\Sdk\platform-tools"`

### "ANDROID_HOME is not set"

**Solution:**
```powershell
$env:ANDROID_HOME = "$env:LOCALAPPDATA\Android\Sdk"
```

### "Unable to load script"

**Solution:**
- Make sure Metro bundler is running
- Clear cache: `npm start -- --reset-cache`

### "SDK location not found"

**Solution:**
Create `android/local.properties`:
```
sdk.dir=C:\\Users\\p.selvaraj\\AppData\\Local\\Android\\Sdk
```

### Build fails with Gradle error

**Solution:**
```powershell
cd android
.\gradlew clean
cd ..
npm run android
```

---

## First Build

**Important:** The first build will take 10-15 minutes because:
- Gradle downloads dependencies
- Android SDK downloads build tools
- React Native compiles native modules

**Subsequent builds:** 1-2 minutes

---

## Running the App

### Start Metro Bundler (Terminal 1)

```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" start
```

Keep this running!

### Run on Android (Terminal 2)

```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" run android
```

### What Happens:

1. ✅ Metro bundler starts (JavaScript bundler)
2. ✅ Gradle builds Android app (first time: 10-15 mins)
3. ✅ App installs on emulator/device
4. ✅ App launches automatically
5. ✅ You see the Recipe App!

---

## Hot Reload

Once running, changes auto-reload:

- Save file → App reloads automatically
- Or press `R` twice in Metro terminal
- Or shake device → Reload

---

## Development Workflow

1. Start Metro: `npm start`
2. Run Android: `npm run android`
3. Make code changes
4. See changes instantly (hot reload)
5. Debug with Chrome DevTools

---

## Next Steps After Setup

1. ✅ Set ANDROID_HOME
2. ✅ Add SDK to PATH
3. ✅ Restart PowerShell
4. ✅ Verify with `adb version`
5. ✅ Start emulator
6. ✅ Run `npm run android`
7. ✅ Enjoy your Recipe App!

---

**Ready?** Start with Step 1 above! 🚀
