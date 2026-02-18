# Build APK Guide

## Method 1: Using Gradle Command (Recommended)

### Build Debug APK (for testing)
```cmd
cd android-mobile-app
gradlew assembleDebug
```

The APK will be located at:
`app/build/outputs/apk/debug/app-debug.apk`

### Build Release APK (for distribution)
```cmd
cd android-mobile-app
gradlew assembleRelease
```

The APK will be located at:
`app/build/outputs/apk/release/app-release-unsigned.apk`

## Method 2: Using Android Studio

1. Open the project in Android Studio
2. Go to: **Build** → **Build Bundle(s) / APK(s)** → **Build APK(s)**
3. Wait for the build to complete
4. Click "locate" in the notification to find the APK

## Installing APK on Mobile Device

### Option A: USB Connection
1. Enable **Developer Options** on your phone:
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
2. Enable **USB Debugging**:
   - Settings → Developer Options → USB Debugging
3. Connect phone to computer via USB
4. Run: `adb install app/build/outputs/apk/debug/app-debug.apk`

### Option B: Direct Transfer
1. Copy the APK file to your phone (via USB, email, cloud storage, etc.)
2. On your phone, enable **Install from Unknown Sources**:
   - Settings → Security → Unknown Sources (enable)
3. Open the APK file on your phone
4. Tap "Install"

## Signing Release APK (Optional - for Play Store)

To create a signed release APK:

1. Generate keystore:
```cmd
keytool -genkey -v -keystore my-release-key.jks -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
```

2. Add to `app/build.gradle`:
```gradle
android {
    signingConfigs {
        release {
            storeFile file("my-release-key.jks")
            storePassword "your-password"
            keyAlias "my-key-alias"
            keyPassword "your-password"
        }
    }
    buildTypes {
        release {
            signingConfig signingConfigs.release
        }
    }
}
```

3. Build signed APK:
```cmd
gradlew assembleRelease
```

## Troubleshooting

- If build fails, run: `gradlew clean`
- Make sure Java/JDK is installed
- Check that ANDROID_HOME environment variable is set
