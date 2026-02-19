# 🚀 Running the Android Recipe App

## ✅ Android Studio Detected!

Android Studio is installed on your system. The app is being opened now.

---

## Quick Start Guide

### Option 1: Run in Android Studio (Recommended)

**Android Studio should be opening now with your project.**

Once Android Studio opens:

1. **Wait for Gradle Sync**
   - Android Studio will automatically sync Gradle
   - Wait for "Gradle sync finished" message (bottom right)
   - This may take 2-5 minutes on first open

2. **Select Device**
   - Click the device dropdown (top toolbar)
   - Choose one:
     - **Emulator** - Virtual device (if you have one set up)
     - **Physical Device** - Your phone connected via USB

3. **Run the App**
   - Click the green "Run" button (▶️) in the toolbar
   - Or press `Shift + F10`
   - The app will build and launch

---

### Option 2: Create Emulator (If You Don't Have One)

If you don't have an emulator:

1. **Open Device Manager**
   - Click "Device Manager" icon (phone icon) in right sidebar
   - Or: Tools → Device Manager

2. **Create Virtual Device**
   - Click "Create Device"
   - Select: Pixel 6 or any phone
   - Click "Next"

3. **Select System Image**
   - Choose: Android 13 (API 33) or latest
   - Click "Download" if needed
   - Click "Next" → "Finish"

4. **Run on Emulator**
   - Select your new emulator from device dropdown
   - Click Run button (▶️)

---

### Option 3: Run on Physical Phone

**Requirements:**
- Android phone (Android 5.0+)
- USB cable
- USB Debugging enabled

**Steps:**

1. **Enable Developer Mode on Phone**
   - Settings → About Phone
   - Tap "Build Number" 7 times
   - You'll see "You are now a developer!"

2. **Enable USB Debugging**
   - Settings → System → Developer Options
   - Turn on "USB Debugging"

3. **Connect Phone**
   - Connect phone to computer via USB
   - On phone: Allow USB debugging when prompted
   - Select "File Transfer" mode

4. **Run from Android Studio**
   - Your phone should appear in device dropdown
   - Click Run button (▶️)
   - App will install and launch on your phone

---

## What You'll See

When the app runs, you'll see:

**Main Screen:**
- 📱 Recipe list with images
- 🔍 Search bar at top
- 🎯 Filter menu (top right)
- 5 recipes displayed in cards

**Features to Try:**
1. **Search** - Type "chicken" in search bar
2. **Filter** - Click menu → Select category
3. **View Recipe** - Tap any recipe card
4. **See Details** - View ingredients and instructions
5. **Go Back** - Use back button to return

---

## Recipes Included

1. 🍝 **Spaghetti Carbonara** (Italian)
2. 🍗 **Chicken Tikka Masala** (Indian)
3. 🥗 **Caesar Salad** (Salad)
4. 🍪 **Chocolate Chip Cookies** (Dessert)
5. 🥘 **Vegetable Stir Fry** (Asian)

---

## Build APK (To Share with Others)

If you want to create an APK file:

1. **Build APK**
   - Menu: Build → Build Bundle(s) / APK(s) → Build APK(s)
   - Wait for build to complete
   - Click "locate" in notification

2. **APK Location**
   ```
   android-mobile-app\app\build\outputs\apk\debug\app-debug.apk
   ```

3. **Share APK**
   - Copy this file to your phone
   - Install on any Android device
   - Or share with others

---

## Troubleshooting

### Gradle Sync Failed

**Solution:**
1. File → Invalidate Caches → Restart
2. Wait for Android Studio to restart
3. Let Gradle sync again

### Build Errors

**Solution:**
1. Build → Clean Project
2. Build → Rebuild Project
3. Try running again

### Device Not Detected

**For Emulator:**
- Make sure emulator is running
- Try restarting emulator

**For Physical Phone:**
- Check USB cable is connected
- Enable USB Debugging on phone
- Try different USB port
- Install phone drivers if needed

### App Crashes on Launch

**Solution:**
1. Check minimum Android version (5.0+)
2. View Logcat in Android Studio for errors
3. Try on different device/emulator

---

## Android Studio Shortcuts

**Useful Shortcuts:**
- `Shift + F10` - Run app
- `Ctrl + F9` - Build project
- `Alt + Shift + F10` - Run menu
- `Shift + F9` - Debug app
- `Ctrl + Shift + A` - Find action

---

## Project Structure in Android Studio

```
android-mobile-app/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/recipeapp/
│   │       │   ├── MainActivity.kt          ← Main screen
│   │       │   ├── RecipeAdapter.kt         ← List adapter
│   │       │   ├── RecipeDetailActivity.kt  ← Detail screen
│   │       │   └── models/
│   │       │       └── Recipe.kt            ← Data model
│   │       ├── res/
│   │       │   ├── layout/                  ← UI layouts
│   │       │   ├── values/                  ← Strings, colors
│   │       │   └── menu/                    ← App menu
│   │       └── AndroidManifest.xml          ← App config
│   └── build.gradle                         ← App dependencies
└── build.gradle                             ← Project config
```

---

## Next Steps

### Customize the App

1. **Add More Recipes**
   - Open `MainActivity.kt`
   - Find `loadRecipes()` function
   - Add more Recipe objects

2. **Change Colors**
   - Open `res/values/colors.xml`
   - Modify color values

3. **Update Strings**
   - Open `res/values/strings.xml`
   - Change app name or text

4. **Modify Layout**
   - Open layout XML files
   - Adjust UI elements

### Learn More

- Android Docs: https://developer.android.com
- Kotlin Docs: https://kotlinlang.org
- Material Design: https://material.io

---

## Status

**Android Studio:** ✅ Installed  
**Project:** ✅ Ready to run  
**Location:** C:\Users\p.selvaraj\Documents\KIRO_PJT\android-mobile-app

**Next:** Wait for Android Studio to open and Gradle to sync, then click Run! ▶️

---

**Enjoy your Recipe App!** 🍳📱
