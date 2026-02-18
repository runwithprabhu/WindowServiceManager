# Quick Build Instructions

## Build APK using Android Studio (Easiest Method)

1. **Open Project in Android Studio**
   - Launch Android Studio
   - Click "Open" and select the `android-mobile-app` folder
   - Wait for Gradle sync to complete

2. **Build APK**
   - Go to menu: **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
   - Wait for build to complete (you'll see a notification)
   - Click "locate" in the notification

3. **APK Location**
   - The APK will be at: `app/build/outputs/apk/debug/app-debug.apk`

## Install on Your Phone

### Method 1: Direct Install via USB
1. Enable Developer Mode on your phone:
   - Settings → About Phone → Tap "Build Number" 7 times
2. Enable USB Debugging:
   - Settings → Developer Options → USB Debugging (ON)
3. Connect phone to computer
4. In Android Studio, click the green "Run" button
5. Select your device from the list

### Method 2: Transfer APK File
1. Build the APK (see above)
2. Copy `app-debug.apk` to your phone (via USB, email, Google Drive, etc.)
3. On your phone:
   - Enable "Install from Unknown Sources" in Settings
   - Open the APK file
   - Tap "Install"

## Alternative: Build from Command Line

If you have Android SDK installed:

```cmd
cd android-mobile-app
gradlew assembleDebug
```

The APK will be created at: `app\build\outputs\apk\debug\app-debug.apk`

## Requirements

- Android Studio (latest version)
- JDK 11 or higher
- Android SDK (API 21+)
- For phone: Android 5.0 (Lollipop) or higher

## Troubleshooting

- **Gradle sync fails**: File → Invalidate Caches → Restart
- **Build errors**: Build → Clean Project, then Build → Rebuild Project
- **Can't install on phone**: Make sure "Unknown Sources" is enabled in phone settings
