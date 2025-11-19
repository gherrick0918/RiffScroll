# RiffScroll

⚔️ **A Guitar Practice Routine App with Old School RPG Theme**

## Overview

RiffScroll is an Android app that dynamically generates balanced guitar practice routines with an old school RPG theme. The app helps guitarists structure their practice sessions across techniques, creativity, and song learning.

## Features

- **Dynamic Routine Generation**: Creates balanced practice routines covering techniques, creativity, and songs
- **Exercise Display**: Shows detailed instructions for each exercise
- **Timer Integration**: Built-in timer for practice segments
- **Metronome Support**: Adjustable metronome (40-240 BPM) for timing-based exercises
- **RPG Theme**: Old school RPG-inspired UI with level progression and XP system
- **Responsive Design**: Supports portrait and landscape modes on phones and tablets
- **Progress Tracking**: Track completed routines, practice time, and level advancement

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## Project Structure

```
app/src/main/kotlin/com/riffscroll/
├── data/                      # Data models and repository
│   ├── Models.kt             # Exercise, Routine, Session data models
│   └── ExerciseRepository.kt # Exercise data and routine generation
├── viewmodel/                 # ViewModels
│   └── PracticeViewModel.kt  # Manages practice state and logic
├── ui/                        # UI components
│   ├── RpgComponents.kt      # Reusable RPG-themed components
│   ├── HomeScreen.kt         # Main screen with routine generation
│   └── PracticeSessionScreen.kt # Active practice session UI
└── MainActivity.kt            # Entry point
```

## Exercise Categories

### Techniques
- Chromatic scale practice
- Alternate picking drills
- String skipping exercises
- Hammer-on & pull-off practice
- Bending accuracy

### Creativity
- Improvisation in pentatonic
- Melodic composition
- Rhythm creation
- Modal exploration

### Songs
- Song section practice
- Full song play-through
- Song memorization
- Cover song learning

## Building the App

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run tests
./gradlew test
```

## Requirements

- Android Studio Hedgehog or later
- Gradle 8.2+
- JDK 17+

## License

Copyright 2024 RiffScroll