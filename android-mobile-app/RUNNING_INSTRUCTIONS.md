# 🚀 Running the Recipe App - Quick Guide

## ✅ Android Studio is Opening!

The Kotlin Recipe App is loading in Android Studio now.

---

## 📋 What to Do Next (3 Steps)

### Step 1: Wait for Gradle Sync (1-2 minutes)

Android Studio will automatically:
- Load the project
- Sync Gradle dependencies
- Index files

**Look for:** "Gradle sync finished" message at the bottom

---

### Step 2: Select Device

At the top toolbar:
1. Click the device dropdown
2. Select your emulator (e.g., "Pixel_6_API_33")
3. If no emulator, click "Device Manager" → Start emulator

---

### Step 3: Run the App

Click the green **Run** button (▶️) at the top toolbar

Or press: `Shift + F10`

**The app will:**
1. Build (30 seconds - 1 minute)
2. Install on emulator
3. Launch automatically!

---

## 📱 What You'll See

### Home Screen:
- **5 Recipe Cards** with images
- **Search Bar** at the top
- **Filter Menu** (top right - 3 dots)
- Tap any card to see details

### Recipes Included:
1. 🍝 **Spaghetti Carbonara** (Italian)
2. 🍗 **Chicken Tikka Masala** (Indian)
3. 🥗 **Caesar Salad** (Salad)
4. 🍪 **Chocolate Chip Cookies** (Dessert)
5. 🥘 **Vegetable Stir Fry** (Asian)

### Recipe Detail Screen:
- Large recipe image
- Ingredients list
- Step-by-step instructions
- Prep time, cook time, servings

---

## 🎯 Features to Try

1. **Search** - Type "chicken" in search bar
2. **Filter** - Click menu (⋮) → Select category
3. **View Recipe** - Tap any recipe card
4. **Read Instructions** - Scroll through cooking steps
5. **Go Back** - Use back button to return

---

## 🐛 Troubleshooting

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

### No Emulator Available

**Solution:**
1. Tools → Device Manager
2. Click "Create Device"
3. Select "Pixel 6"
4. Choose "Android 13 (API 33)"
5. Click "Finish"
6. Start the emulator

### App Won't Install

**Solution:**
1. Check emulator is fully booted
2. Try: Build → Clean Project
3. Run again

---

## ⌨️ Useful Shortcuts

- `Shift + F10` - Run app
- `Ctrl + F9` - Build project
- `Shift + F9` - Debug app
- `Alt + Shift + F10` - Run menu

---

## 📊 Project Structure

```
android-mobile-app/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/example/recipeapp/
│           │   ├── MainActivity.kt          # Main screen
│           │   ├── RecipeAdapter.kt         # List adapter
│           │   ├── RecipeDetailActivity.kt  # Detail screen
│           │   └── models/
│           │       └── Recipe.kt            # Data model
│           └── res/
│               ├── layout/                  # UI layouts
│               ├── values/                  # Strings, colors
│               └── menu/                    # App menu
```

---

## 🎨 Customization Ideas

### Add More Recipes:
1. Open `MainActivity.kt`
2. Find `loadRecipes()` function
3. Add new `Recipe` objects

### Change Colors:
1. Open `res/values/colors.xml`
2. Modify color values
3. Run app to see changes

### Update Strings:
1. Open `res/values/strings.xml`
2. Change app name or text
3. Rebuild app

---

## ✅ Quick Checklist

Before running:
- [ ] Android Studio opened
- [ ] Gradle sync completed
- [ ] Emulator selected or started
- [ ] Green Run button visible

Then:
- [ ] Click Run button (▶️)
- [ ] Wait for build (30-60 seconds)
- [ ] App launches on emulator
- [ ] Enjoy your Recipe App!

---

## 🎉 You're Ready!

**Android Studio is opening now.**

Once it loads:
1. Wait for Gradle sync
2. Select emulator
3. Click Run (▶️)

**Your Recipe App will launch in 1-2 minutes!** 🚀

---

## 📚 Additional Resources

- **Build APK:** Build → Build Bundle(s) / APK(s) → Build APK(s)
- **APK Location:** `app/build/outputs/apk/debug/app-debug.apk`
- **Documentation:** See `README.md` and `BUILD_INSTRUCTIONS.md`

---

**Enjoy your Recipe App!** 🍳📱
