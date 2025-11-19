# Quick Start Guide

## Getting Started with RiffScroll

### Prerequisites

Before you can build and run RiffScroll, you'll need:

1. **Android Studio** - [Download](https://developer.android.com/studio)
   - Version: Hedgehog (2023.1.1) or later recommended
   
2. **Android SDK**
   - Minimum API 24 (Android 7.0)
   - Target API 34 (Android 14)
   - Install via Android Studio SDK Manager

3. **Java Development Kit (JDK)**
   - Version 17 or later
   - Usually included with Android Studio

### Step 1: Open the Project

1. Launch Android Studio
2. Click **"Open"** or **"Open an Existing Project"**
3. Navigate to the `RiffScroll` directory
4. Click **"OK"**
5. Wait for Gradle to sync (may take a few minutes on first open)

### Step 2: Configure Android SDK

If prompted:
1. Go to **File → Project Structure → SDK Location**
2. Ensure Android SDK location is set
3. Click **"Apply"** and **"OK"**

### Step 3: Build the Project

**Option A: Using Android Studio UI**
1. Go to **Build → Make Project** (or press Ctrl+F9 / Cmd+F9)
2. Wait for build to complete
3. Check the build output for any errors

**Option B: Using Command Line**
```bash
cd /path/to/RiffScroll
./gradlew build
```

### Step 4: Run on Device or Emulator

**Running on Physical Device:**

1. **Enable Developer Options** on your device:
   - Go to Settings → About Phone
   - Tap "Build Number" 7 times
   - Go back to Settings → System → Developer Options
   - Enable "USB Debugging"

2. **Connect Device**:
   - Connect your phone/tablet via USB
   - Accept the USB debugging prompt on device
   - Device should appear in Android Studio's device dropdown

3. **Run App**:
   - Click the green "Run" button (▶) in Android Studio
   - Or use: `./gradlew installDebug`

**Running on Emulator:**

1. **Create Virtual Device**:
   - In Android Studio: Tools → Device Manager
   - Click "Create Device"
   - Choose a device (e.g., Pixel 4, Pixel Tablet)
   - Select system image (API 34 recommended)
   - Click "Finish"

2. **Start Emulator**:
   - Click the play button next to your virtual device

3. **Run App**:
   - Click the green "Run" button in Android Studio
   - Select your emulator from the dropdown

### Step 5: Using the App

**Generate Your First Routine:**

1. Open the app - you'll see the home screen with your progress (Level 1, 0 XP)

2. Adjust practice duration:
   - Use the slider to select 15-90 minutes
   - Default is 45 minutes

3. Click **"🎲 Generate New Routine"**
   - App creates a balanced routine
   - Shows 6+ exercises covering all categories

4. Review the routine:
   - Each exercise shows name, description, category, duration, and BPM
   - Check the total time

5. Click **"⚔️ Start Practice"** when ready

**During Practice:**

1. **Current Exercise**:
   - Name and description at top
   - Step-by-step instructions below
   - Timer shows elapsed time vs. target

2. **Timer**:
   - Counts up from 00:00
   - Shows progress bar toward target time
   - Continues running until you pause

3. **Metronome** (for timed exercises):
   - Toggle on/off with switch
   - Adjust BPM with slider (40-240)
   - Quick +5/-5 buttons on sides

4. **Controls**:
   - **⏸ Pause**: Stop timer and metronome
   - **▶ Resume**: Continue from where you paused
   - **Next ➜**: Move to next exercise
   - **✕** (top right): End practice session

5. **Complete Routine**:
   - Finish all exercises to gain XP
   - 2 XP per minute practiced
   - Level up when XP bar fills!

### Testing Different Screen Sizes

**Phone Testing:**
- Test in portrait and landscape
- Rotate device to verify layout adapts
- Check that all elements are accessible

**Tablet Testing:**
- Open on Galaxy Tab S7+ or similar
- Test both orientations
- Verify two-column layout in landscape

### Troubleshooting

**Gradle Sync Failed:**
```
Solution: 
- Check internet connection
- File → Invalidate Caches → Invalidate and Restart
- Ensure JDK 17+ is installed
```

**Build Errors:**
```
Solution:
- Update Android SDK to latest version
- Tools → SDK Manager → update "Android SDK Build-Tools"
- Clean project: Build → Clean Project
- Rebuild: Build → Rebuild Project
```

**App Crashes on Start:**
```
Solution:
- Check Logcat for error messages
- Verify minSdk is API 24 or higher on device
- Try running on different device/emulator
```

**Gradle Wrapper Not Found:**
```bash
# Re-initialize wrapper
gradle wrapper --gradle-version 8.2
```

## Advanced Usage

### Customizing Exercises

Currently exercises are hardcoded in `ExerciseRepository.kt`. To add custom exercises:

1. Open `app/src/main/kotlin/com/riffscroll/data/ExerciseRepository.kt`
2. Add new `Exercise` objects to appropriate list
3. Rebuild and run

### Modifying RPG Theme Colors

Colors are defined in two places:

1. **Resource XML**: `app/src/main/res/values/colors.xml`
2. **Compose Theme**: `app/src/main/kotlin/com/riffscroll/ui/RpgComponents.kt`

### Adjusting XP Gain

Modify in `PracticeViewModel.kt`:
```kotlin
// Line ~172
val xpGained = session.routine.totalDurationMinutes * 2  // Change multiplier
```

## Testing Checklist

- [ ] Generate routine with 15 min duration
- [ ] Generate routine with 45 min duration  
- [ ] Generate routine with 90 min duration
- [ ] Start practice session
- [ ] Verify timer counts correctly
- [ ] Pause and resume session
- [ ] Navigate to next exercise
- [ ] Toggle metronome on/off
- [ ] Adjust metronome BPM
- [ ] Complete full routine
- [ ] Verify XP gain and level progress
- [ ] Rotate to landscape mode
- [ ] Rotate back to portrait mode
- [ ] Test on phone-sized screen
- [ ] Test on tablet-sized screen

## Next Steps

After successfully running the app:

1. **Test thoroughly** on both Pixel 10 Pro XL and Galaxy Tab S7+
2. **Gather feedback** on exercise selection and duration
3. **Consider enhancements**:
   - Audio metronome playback
   - Data persistence (save progress)
   - Custom exercise creation
   - Practice history tracking
   - More exercises in each category

## Resources

- **Android Studio**: https://developer.android.com/studio
- **Jetpack Compose**: https://developer.android.com/jetpack/compose
- **Kotlin**: https://kotlinlang.org/
- **Project Docs**: See DEVELOPMENT.md for architecture details

## Support

If you encounter issues:

1. Check DEVELOPMENT.md troubleshooting section
2. Review Android Studio's Logcat for error messages
3. Verify all prerequisites are installed
4. Try cleaning and rebuilding the project

## License

Copyright 2024 RiffScroll
