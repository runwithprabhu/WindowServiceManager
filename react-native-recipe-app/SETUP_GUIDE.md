# 🚀 React Native Recipe App - Setup Guide

## Quick Start (3 Steps)

### Step 1: Install Dependencies

```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" install
```

**Wait time:** 3-5 minutes (first time)

### Step 2: Start Metro Bundler

```powershell
& "C:\Program Files\nodejs\npm.cmd" start
```

Keep this terminal open!

### Step 3: Run on Android

Open a new terminal:

```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" run android
```

---

## Detailed Setup Instructions

### Prerequisites Check

**✅ You Have:**
- Node.js v24.13.1
- npm v11.8.0
- Android Studio

**❓ Need to Install:**
- React Native CLI (optional, but recommended)

### Install React Native CLI (Optional)

```powershell
& "C:\Program Files\nodejs\npm.cmd" install -g react-native-cli
```

---

## Running on Android

### Option 1: Using npm (Recommended)

**Terminal 1 - Start Metro:**
```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" start
```

**Terminal 2 - Run Android:**
```powershell
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
& "C:\Program Files\nodejs\npm.cmd" run android
```

### Option 2: Using Android Studio

1. Open Android Studio
2. Open folder: `react-native-recipe-app/android`
3. Wait for Gradle sync
4. Click Run button (green play icon)
5. Select emulator or device

---

## Device Setup

### Android Emulator

**Create Emulator in Android Studio:**

1. Tools → Device Manager
2. Create Device
3. Select: Pixel 6
4. System Image: Android 13 (API 33)
5. Click Finish

**Start Emulator:**
- Click play button in Device Manager
- Or run: `emulator -avd Pixel_6_API_33`

### Physical Android Device

**Enable Developer Mode:**
1. Settings → About Phone
2. Tap "Build Number" 7 times

**Enable USB Debugging:**
1. Settings → Developer Options
2. Turn on "USB Debugging"

**Connect Device:**
1. Connect via USB
2. Allow USB debugging on phone
3. Verify: `adb devices`

---

## First Run

### What Happens

1. **Metro Bundler starts** - JavaScript bundler
2. **Dependencies install** - Native modules
3. **App builds** - Gradle build (Android)
4. **App installs** - On device/emulator
5. **App launches** - Recipe App opens!

### Expected Time

- **First run:** 5-10 minutes
- **Subsequent runs:** 1-2 minutes

---

## Project Structure

```
react-native-recipe-app/
├── App.tsx                    # Main app with navigation
├── index.js                   # Entry point
├── package.json               # Dependencies
│
├── src/
│   ├── components/
│   │   └── RecipeCard.tsx    # Recipe card UI
│   │
│   ├── screens/
│   │   ├── HomeScreen.tsx    # Recipe list
│   │   └── RecipeDetailScreen.tsx  # Recipe details
│   │
│   ├── data/
│   │   └── recipes.ts        # Recipe data
│   │
│   └── types/
│       └── Recipe.ts         # TypeScript types
│
├── android/                   # Android native code
└── ios/                       # iOS native code (Mac only)
```

---

## Features

### Home Screen
- 🔍 Search recipes by name
- 🎯 Filter by category (Italian, Indian, Salad, Dessert, Asian)
- 📱 Beautiful recipe cards
- ⏱️ Prep time display

### Recipe Detail Screen
- 🍳 Large recipe emoji
- ⏰ Prep time, cook time, servings
- 🏷️ Category and difficulty badges
- 📝 Ingredients list with bullets
- 📋 Step-by-step instructions with numbers

---

## Development Commands

### Start Metro Bundler
```powershell
& "C:\Program Files\nodejs\npm.cmd" start
```

### Run Android
```powershell
& "C:\Program Files\nodejs\npm.cmd" run android
```

### Clear Cache
```powershell
& "C:\Program Files\nodejs\npm.cmd" start -- --reset-cache
```

### Lint Code
```powershell
& "C:\Program Files\nodejs\npm.cmd" run lint
```

---

## Troubleshooting

### Metro Bundler Won't Start

**Solution:**
```powershell
# Kill existing Metro process
taskkill /F /IM node.exe

# Clear cache and restart
& "C:\Program Files\nodejs\npm.cmd" start -- --reset-cache
```

### Build Errors

**Solution:**
```powershell
cd android
.\gradlew clean
cd ..
& "C:\Program Files\nodejs\npm.cmd" run android
```

### Module Not Found

**Solution:**
```powershell
# Reinstall dependencies
Remove-Item -Recurse -Force node_modules
& "C:\Program Files\nodejs\npm.cmd" install
```

### Device Not Detected

**Check ADB:**
```powershell
adb devices
```

**Restart ADB:**
```powershell
adb kill-server
adb start-server
adb devices
```

### Port 8081 Already in Use

**Solution:**
```powershell
# Find process using port 8081
netstat -ano | findstr :8081

# Kill the process (replace PID)
taskkill /PID <PID> /F

# Or use different port
& "C:\Program Files\nodejs\npm.cmd" start -- --port 8082
```

---

## Hot Reload

React Native supports hot reloading:

**Enable:**
- Shake device (physical)
- Press `Ctrl + M` (emulator)
- Select "Enable Hot Reloading"

**Or:**
- Press `R` twice in Metro terminal

---

## Debugging

### Chrome DevTools

1. Shake device or `Ctrl + M`
2. Select "Debug"
3. Opens Chrome DevTools
4. View console, network, etc.

### React Native Debugger

Download: https://github.com/jhen0409/react-native-debugger

---

## Building APK

### Debug APK (for testing)

```powershell
cd android
.\gradlew assembleDebug
```

**Location:** `android\app\build\outputs\apk\debug\app-debug.apk`

### Release APK (for distribution)

```powershell
cd android
.\gradlew assembleRelease
```

**Location:** `android\app\build\outputs\apk\release\app-release.apk`

---

## Performance Tips

1. **Use Release Build** for testing performance
2. **Enable Hermes** (already enabled)
3. **Optimize Images** - Use appropriate sizes
4. **Lazy Load** - Load data as needed
5. **Memoize Components** - Use React.memo

---

## Next Steps

### Customize the App

1. **Add More Recipes**
   - Edit `src/data/recipes.ts`
   - Add new Recipe objects

2. **Change Colors**
   - Edit styles in screen files
   - Update theme colors

3. **Add Features**
   - Favorites
   - Ratings
   - Share functionality
   - Dark mode

### Deploy

1. **Google Play Store**
   - Create developer account
   - Build release AAB
   - Upload to Play Console

2. **Share APK**
   - Build release APK
   - Share file directly

---

## Resources

- **React Native Docs:** https://reactnative.dev/
- **React Navigation:** https://reactnavigation.org/
- **TypeScript:** https://www.typescriptlang.org/
- **Android Studio:** https://developer.android.com/studio

---

## Quick Reference

**Project Path:**
```
C:\Users\p.selvaraj\Documents\KIRO_PJT\react-native-recipe-app
```

**Start App:**
```powershell
# Terminal 1
& "C:\Program Files\nodejs\npm.cmd" start

# Terminal 2
& "C:\Program Files\nodejs\npm.cmd" run android
```

**Reload App:**
- Press `R` twice in Metro terminal
- Or shake device → Reload

---

**Ready to start?** Follow Step 1, 2, and 3 at the top! 🚀
