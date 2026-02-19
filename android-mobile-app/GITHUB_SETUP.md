# 🚀 GitHub Repository Setup Guide

## Create New Repository on GitHub

### Step 1: Create Repository on GitHub.com

1. Go to: https://github.com/new
2. Fill in the details:
   - **Repository name:** `android-recipe-app`
   - **Description:** `Modern Android recipe app with Kotlin and Material Design`
   - **Visibility:** Public or Private (your choice)
   - **DO NOT** initialize with README, .gitignore, or license (we already have these)
3. Click "Create repository"

### Step 2: Initialize Git (if not already done)

```bash
cd android-mobile-app
git init
git add .
git commit -m "Initial commit: Android Recipe App with Kotlin and Material Design"
```

### Step 3: Connect to GitHub

Replace `YOUR_USERNAME` with your GitHub username:

```bash
git remote add origin https://github.com/YOUR_USERNAME/android-recipe-app.git
git branch -M main
git push -u origin main
```

---

## Quick Setup Commands

### Option 1: Using HTTPS (Recommended for beginners)

```bash
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\android-mobile-app

# Initialize git
git init

# Add all files
git add .

# Commit
git commit -m "Initial commit: Android Recipe App

- Kotlin-based Android app
- Material Design UI
- 5+ recipes with images
- Search and filter functionality
- RecyclerView with custom adapter
- Detail view with ingredients and instructions"

# Add remote (replace YOUR_USERNAME)
git remote add origin https://github.com/YOUR_USERNAME/android-recipe-app.git

# Push to GitHub
git branch -M main
git push -u origin main
```

### Option 2: Using SSH (If you have SSH keys set up)

```bash
cd C:\Users\p.selvaraj\Documents\KIRO_PJT\android-mobile-app

git init
git add .
git commit -m "Initial commit: Android Recipe App"
git remote add origin git@github.com:YOUR_USERNAME/android-recipe-app.git
git branch -M main
git push -u origin main
```

---

## What Will Be Committed

### Source Code
- ✅ Kotlin source files (MainActivity, RecipeAdapter, etc.)
- ✅ XML layouts (activity_main, item_recipe, etc.)
- ✅ Resources (strings, colors, themes)
- ✅ Gradle build files
- ✅ AndroidManifest.xml

### Documentation
- ✅ README.md
- ✅ BUILD_INSTRUCTIONS.md
- ✅ BUILD_APK_GUIDE.md
- ✅ GITHUB_SETUP.md (this file)

### Configuration
- ✅ .gitignore
- ✅ gradle.properties
- ✅ settings.gradle

### What's Excluded (by .gitignore)
- ❌ build/ folders
- ❌ .gradle/ cache
- ❌ .idea/ IDE settings
- ❌ local.properties
- ❌ *.apk files

---

## Verify Setup

After pushing, verify on GitHub:

1. Go to: `https://github.com/YOUR_USERNAME/android-recipe-app`
2. You should see:
   - All source files
   - README.md displayed on the main page
   - Proper folder structure

---

## Clone Instructions (For Others)

Once pushed, others can clone with:

```bash
git clone https://github.com/YOUR_USERNAME/android-recipe-app.git
cd android-recipe-app
```

Then open in Android Studio and build.

---

## Troubleshooting

### "remote origin already exists"
```bash
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/android-recipe-app.git
```

### "Permission denied"
- Make sure you're logged into GitHub
- For HTTPS: You may need a Personal Access Token
- For SSH: Set up SSH keys first

### "Repository not found"
- Check the repository name is correct
- Verify the repository exists on GitHub
- Check your GitHub username

### Large files warning
- APK files are excluded by .gitignore
- If you see warnings, check .gitignore is working

---

## Repository Settings (After Creation)

### Recommended Settings

1. **About Section:**
   - Description: "Modern Android recipe app with Kotlin and Material Design"
   - Topics: `android`, `kotlin`, `material-design`, `recipe-app`, `mobile`

2. **Branch Protection:**
   - Protect main branch (optional)
   - Require pull request reviews

3. **GitHub Pages:**
   - Not needed for Android app

---

## Future Updates

To push new changes:

```bash
git add .
git commit -m "Description of changes"
git push origin main
```

---

## Repository Structure on GitHub

```
android-recipe-app/
├── .gitignore
├── README.md
├── BUILD_INSTRUCTIONS.md
├── BUILD_APK_GUIDE.md
├── GITHUB_SETUP.md
├── build.gradle
├── settings.gradle
├── gradle.properties
├── gradlew.bat
├── gradle/
│   └── wrapper/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/recipeapp/
│           │   ├── MainActivity.kt
│           │   ├── RecipeAdapter.kt
│           │   ├── RecipeDetailActivity.kt
│           │   └── models/
│           │       └── Recipe.kt
│           └── res/
│               ├── layout/
│               ├── values/
│               └── menu/
```

---

## Example Repository Names

Choose one:
- `android-recipe-app` (recommended)
- `recipe-app-android`
- `kotlin-recipe-app`
- `mobile-recipe-app`
- `RecipeApp`

---

## Next Steps After Push

1. ✅ Add repository description on GitHub
2. ✅ Add topics/tags
3. ✅ Create releases (optional)
4. ✅ Add screenshots to README
5. ✅ Set up GitHub Actions for CI/CD (optional)

---

**Ready to push?** Follow the commands above and your Android app will be on GitHub! 🚀
