# Tizen TV Games Collection 🎮

A collection of fun, remote-control-friendly games for Samsung Tizen Smart TVs.

## Games Included

### 1. 🐍 Snake Game
Classic snake game where you control a growing snake and eat food while avoiding walls and yourself.

**Controls:**
- Arrow Keys: Move snake
- OK: Pause/Resume
- Back: Restart

**Features:**
- Smooth animations
- Score tracking
- High score saving
- Increasing difficulty

### 2. 🧠 Memory Game
Match pairs of emoji cards to test your memory and concentration.

**Controls:**
- Arrow Keys: Navigate cards
- OK: Flip card
- Back: Restart

**Features:**
- 16 cards (8 pairs)
- Move counter
- Timer
- Win detection

### 3. 🎮 Block Puzzle (Tetris-style)
Arrange falling blocks to clear lines and score points.

**Controls:**
- Left/Right: Move piece
- Up: Rotate piece
- Down: Soft drop
- OK: Pause

**Features:**
- 7 different piece types
- Level progression
- Line clearing
- Next piece preview

### 4. 🏓 Pong Game (Coming Soon)
Classic pong game with AI opponent.

### 5. 🚀 Space Shooter (Coming Soon)
Shoot asteroids and enemies in space.

### 6. ❓ Quiz Game (Coming Soon)
Test your knowledge with trivia questions.

## How to Play

### On Tizen TV

1. **Copy files to TV:**
   ```bash
   sdb connect YOUR_TV_IP:26101
   sdb push TV_Games /opt/usr/apps/
   ```

2. **Open in TV browser:**
   - Launch TV browser
   - Navigate to file location
   - Open `index.html`

3. **Or deploy as Tizen app:**
   - Create Tizen project
   - Copy game files
   - Build and deploy

### In Browser (Testing)

1. **Open index.html:**
   ```bash
   cd TV_Games
   start index.html
   ```

2. **Use keyboard:**
   - Arrow keys for navigation
   - Enter for OK button
   - Escape for Back button

## Remote Control Mapping

All games support standard Tizen TV remote controls:

| Remote Button | Key Code | Function |
|--------------|----------|----------|
| ← Left | 37 | Move/Navigate left |
| → Right | 39 | Move/Navigate right |
| ↑ Up | 38 | Move/Navigate up |
| ↓ Down | 40 | Move/Navigate down |
| OK/Enter | 13 | Select/Confirm |
| Back | 10009 | Return/Restart |
| Exit | - | Close app |

## Features

### TV-Optimized
- 1920x1080 Full HD resolution
- Large, readable fonts
- High contrast colors
- Smooth animations

### Remote-Friendly
- Simple controls
- Clear visual feedback
- Focus indicators
- Easy navigation

### Performance
- Lightweight code
- No external dependencies
- Fast loading
- Smooth gameplay

## File Structure

```
TV_Games/
├── index.html              # Game launcher/menu
├── snake-game.html         # Snake game
├── memory-game.html        # Memory card game
├── block-puzzle.html       # Tetris-style puzzle
├── pong-game.html         # Pong (coming soon)
├── space-shooter.html     # Space shooter (coming soon)
├── quiz-game.html         # Quiz game (coming soon)
└── README.md              # This file
```

## Customization

### Change Colors

Edit the CSS gradient in each game file:

```css
body {
    background: linear-gradient(135deg, #YOUR_COLOR1 0%, #YOUR_COLOR2 100%);
}
```

### Adjust Difficulty

**Snake Game:**
```javascript
let gameSpeed = 100; // Lower = faster
```

**Block Puzzle:**
```javascript
let dropInterval = 1000; // Lower = faster
```

**Memory Game:**
```javascript
const emojis = ['🍎', '🍌', ...]; // Add more pairs
```

## Adding New Games

1. **Create new HTML file:**
   ```html
   <!DOCTYPE html>
   <html>
   <head>
       <title>My Game</title>
       <!-- Add styles -->
   </head>
   <body>
       <!-- Add game canvas/elements -->
       <script>
           // Add game logic
       </script>
   </body>
   </html>
   ```

2. **Add to launcher (index.html):**
   ```html
   <div class="game-card" data-game="my-game.html">
       <span class="game-icon">🎯</span>
       <div class="game-title">My Game</div>
       <div class="game-description">Description here</div>
   </div>
   ```

3. **Implement remote controls:**
   ```javascript
   document.addEventListener('keydown', (e) => {
       switch(e.keyCode) {
           case 37: // Left
           case 38: // Up
           case 39: // Right
           case 40: // Down
           case 13: // OK
           case 10009: // Back
       }
   });
   ```

## Best Practices

### 1. Performance
- Use `requestAnimationFrame` for smooth animations
- Limit canvas redraws
- Optimize collision detection
- Clear intervals on game over

### 2. User Experience
- Show clear instructions
- Provide visual feedback
- Save high scores locally
- Handle pause/resume

### 3. Accessibility
- Large touch targets
- High contrast
- Clear focus indicators
- Simple controls

## Troubleshooting

### Game Not Loading
- Check file paths are correct
- Verify HTML syntax
- Check browser console for errors

### Controls Not Working
- Ensure event listeners are attached
- Check key codes match
- Test in browser first

### Performance Issues
- Reduce animation complexity
- Lower frame rate
- Optimize game loop
- Clear unused objects

## Browser Compatibility

- ✅ Chrome
- ✅ Firefox
- ✅ Edge
- ✅ Tizen Browser
- ✅ Safari

## Future Enhancements

- [ ] Add more games
- [ ] Multiplayer support
- [ ] Online leaderboards
- [ ] Sound effects
- [ ] Music
- [ ] Achievements
- [ ] Save game progress
- [ ] Difficulty levels

## Credits

Created for Samsung Tizen Smart TVs
Optimized for remote control gameplay

## License

Free to use and modify for personal projects.

---

**Have fun gaming on your TV!** 🎮📺✨
