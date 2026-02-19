# 🚀 React Native Setup - Final Instructions

## ✅ What's Ready

- ✅ React Native project created
- ✅ Dependencies installed
- ✅ Setup scripts created
- ✅ Documentation complete

## 🎯 Complete Setup (5 Steps)

### Step 1: Run Setup Script

**Double-click this file:**
```
setup-environment.bat
```

Or run in PowerShell:
```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
.\setup-environment.bat
```

This will:
- Set ANDROID_HOME environment variable
- Add Android SDK to PATH
- Create android/local.properties

---

### Step 2: Restart PowerShell

**CRITICAL:** Close and reopen PowerShell to load new environment variables.

---

### Step 3: Verify Setup

Open new PowerShell and run:

```powershell
# Check ANDROID_HOME
echo $env:ANDROID_HOME
# Should show: C:\Users\p.selvaraj\AppData\Local\Android\Sdk

# Check adb
adb version
# Should show: Android Debug Bridge version

# Check devices
adb devices
# Should list connected devices
```

---

### Step 4: Start Android Emulator

**Option A: Android Studio (Easiest)**
1. Open Android Studio
2. Tools → Device Manager
3. Click ▶️ on any emulator

**Option B: Command Line**
```powershell
# List emulators
emulator -list-avds

# Start emulator (replace with your AVD name)
emulator -avd Pixel_6_API_33
```

---

### Step 5: Run React Native App

**Terminal 1 - Start Metro Bundler:**
```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" start
```

**Keep this terminal open!**

**Terminal 2 - Run Android App:**
```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" run android
```

---

## ⏱️ Expected Time

- **First build:** 10-15 minutes (Gradle downloads dependencies)
- **Subsequent builds:** 1-2 minutes
- **Hot reload:** Instant

---

## 🎉 What You'll See

Once the app launches:

**Home Screen:**
- 5 recipe cards with emojis
- Search bar at top
- Category filter chips
- Tap any card to see details

**Recipe Detail:**
- Large recipe emoji
- Prep/cook time, servings
- Ingredients list
- Step-by-step instructions

---

## 🐛 Troubleshooting

### "adb is not recognized"

**Solution:**
- Make sure you restarted PowerShell after running setup
- Or manually add to PATH in System Settings

### "Unable to load script"

**Solution:**
- Make sure Metro bundler is running (Terminal 1)
- Try: `npm start -- --reset-cache`

### "SDK location not found"

**Solution:**
- Check `android/local.properties` exists
- Should contain: `sdk.dir=C:\\Users\\p.selvaraj\\AppData\\Local\\Android\\Sdk`

### Build fails

**Solution:**
```powershell
cd android
.\gradlew clean
cd ..
npm run android
```

### "No connected devices"

**Solution:**
- Start emulator first
- Or connect physical device via USB
- Verify with: `adb devices`

---

## 📱 Alternative: Use Expo (Easier)

If React Native setup is too complex, try Expo:

```powershell
# Install Expo CLI
& "C:\Program Files\nodejs\npm.cmd" install -g expo-cli

# Create Expo project
npx create-expo-app RecipeAppExpo --template blank-typescript

# Run it
cd RecipeAppExpo
npx expo start
```

Then scan QR code with Expo Go app on your phone!

---

## 📚 Documentation

- `REACT_NATIVE_SETUP.md` - Complete setup guide
- `RUN_SETUP.txt` - Quick reference
- `setup-environment.bat` - Setup script
- `README.md` - Project overview

---

## ✅ Quick Checklist

Before running the app:

- [ ] Run `setup-environment.bat`
- [ ] Restart PowerShell
- [ ] Verify `adb version` works
- [ ] Start emulator or connect device
- [ ] Run `npm start` (Terminal 1)
- [ ] Run `npm run android` (Terminal 2)

---

## 🎯 Summary

**To run the app:**

1. **Setup:** Run `setup-environment.bat` (once)
2. **Restart:** Close and reopen PowerShell
3. **Verify:** Run `adb version`
4. **Emulator:** Start Android emulator
5. **Metro:** Run `npm start`
6. **Android:** Run `npm run android`

**First build takes 10-15 minutes, then you're done!**

---

**Ready?** Start with Step 1 above! 🚀

**Need help?** See `REACT_NATIVE_SETUP.md` for detailed troubleshooting.
