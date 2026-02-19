# 📱 React Native Recipe App

A modern, cross-platform mobile recipe application built with React Native and TypeScript.

## ✨ Features

- **Cross-Platform** - Works on both iOS and Android
- **Modern UI** - Beautiful Material Design-inspired interface
- **Search Functionality** - Find recipes quickly
- **Category Filtering** - Filter by cuisine type
- **Recipe Details** - View ingredients and step-by-step instructions
- **TypeScript** - Type-safe code
- **React Navigation** - Smooth navigation between screens
- **Responsive Design** - Optimized for all screen sizes

## 🍳 Recipes Included

1. 🍝 Spaghetti Carbonara (Italian)
2. 🍗 Chicken Tikka Masala (Indian)
3. 🥗 Caesar Salad (Salad)
4. 🍪 Chocolate Chip Cookies (Dessert)
5. 🥘 Vegetable Stir Fry (Asian)

## 🛠️ Tech Stack

- **React Native** 0.73.0
- **TypeScript** 5.0.4
- **React Navigation** 6.x
- **React Native Gesture Handler**
- **React Native Reanimated**
- **React Native Vector Icons**

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Node.js** 18+ (you have v24.13.1 ✅)
- **npm** or **yarn**
- **React Native CLI**
- **Android Studio** (for Android development)
- **Xcode** (for iOS development - Mac only)
- **JDK** 11 or higher

## 🚀 Installation

### Step 1: Install Dependencies

```bash
cd react-native-recipe-app
npm install
```

### Step 2: Install iOS Dependencies (Mac only)

```bash
cd ios
pod install
cd ..
```

## 📱 Running the App

### Android

```bash
# Start Metro bundler
npm start

# In another terminal, run Android
npm run android
```

Or use Android Studio:
1. Open Android Studio
2. Open the `android` folder
3. Click Run button

### iOS (Mac only)

```bash
# Start Metro bundler
npm start

# In another terminal, run iOS
npm run ios
```

Or use Xcode:
1. Open `ios/RecipeApp.xcworkspace` in Xcode
2. Select simulator or device
3. Click Run button

## 📂 Project Structure

```
react-native-recipe-app/
├── App.tsx                      # Main app component with navigation
├── index.js                     # App entry point
├── package.json                 # Dependencies
├── tsconfig.json                # TypeScript configuration
├── babel.config.js              # Babel configuration
├── metro.config.js              # Metro bundler configuration
│
├── src/
│   ├── components/
│   │   └── RecipeCard.tsx       # Recipe card component
│   │
│   ├── screens/
│   │   ├── HomeScreen.tsx       # Main screen with recipe list
│   │   └── RecipeDetailScreen.tsx # Recipe detail screen
│   │
│   ├── data/
│   │   └── recipes.ts           # Recipe data
│   │
│   └── types/
│       └── Recipe.ts            # TypeScript interfaces
│
├── android/                     # Android native code
└── ios/                         # iOS native code
```

## 🎨 Screens

### Home Screen
- Search bar for finding recipes
- Category filter chips
- Recipe cards with images and info
- Tap to view details

### Recipe Detail Screen
- Large recipe image
- Prep time, cook time, servings
- Category and difficulty badges
- Ingredients list
- Step-by-step instructions

## 🔧 Development

### Start Metro Bundler

```bash
npm start
```

### Clear Cache

```bash
npm start -- --reset-cache
```

### Run Tests

```bash
npm test
```

### Lint Code

```bash
npm run lint
```

## 📦 Building for Production

### Android APK

```bash
cd android
./gradlew assembleRelease
```

APK location: `android/app/build/outputs/apk/release/app-release.apk`

### Android AAB (for Play Store)

```bash
cd android
./gradlew bundleRelease
```

AAB location: `android/app/build/outputs/bundle/release/app-release.aab`

### iOS (Mac only)

1. Open `ios/RecipeApp.xcworkspace` in Xcode
2. Select "Any iOS Device" as target
3. Product → Archive
4. Follow App Store submission process

## 🎯 Features to Add

- [ ] Add more recipes
- [ ] Favorite recipes
- [ ] Recipe ratings
- [ ] Share recipes
- [ ] Dark mode
- [ ] Multiple languages
- [ ] Offline support
- [ ] Recipe search by ingredients
- [ ] Shopping list
- [ ] Cooking timer

## 🐛 Troubleshooting

### Metro Bundler Issues

```bash
# Clear cache and restart
npm start -- --reset-cache

# Or
npx react-native start --reset-cache
```

### Android Build Errors

```bash
cd android
./gradlew clean
cd ..
npm run android
```

### iOS Build Errors (Mac)

```bash
cd ios
pod deintegrate
pod install
cd ..
npm run ios
```

### Module Not Found

```bash
# Reinstall dependencies
rm -rf node_modules
npm install

# iOS only
cd ios
pod install
cd ..
```

## 📱 Device Requirements

### Android
- Minimum SDK: 21 (Android 5.0 Lollipop)
- Target SDK: 34 (Android 14)

### iOS
- Minimum iOS: 13.0
- Target iOS: 17.0

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

MIT License

## 🔗 Resources

- [React Native Docs](https://reactnative.dev/)
- [React Navigation](https://reactnavigation.org/)
- [TypeScript](https://www.typescriptlang.org/)
- [React Native Community](https://github.com/react-native-community)

---

**Version:** 1.0.0  
**Last Updated:** February 18, 2026  
**Platform:** iOS & Android
