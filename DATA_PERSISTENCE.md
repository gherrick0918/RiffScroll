# Data Persistence Implementation

## Overview
This document describes the implementation of data persistence in RiffScroll using SharedPreferences and Gson for JSON serialization.

## What Was Implemented

### 1. PersistenceManager
**File**: `app/src/main/kotlin/com/riffscroll/data/PersistenceManager.kt`

A new class that handles all data persistence operations using SharedPreferences:
- User progress (level, XP, stats)
- Saved routines
- Schedules
- Calendar schedules
- Practice schedule plans

**Key Features**:
- Uses Gson for JSON serialization/deserialization
- Type-safe loading with error handling
- Simple API for saving and loading all data types
- `clearAllData()` method for testing and user data reset

### 2. RoutineRepository Enhancement
**File**: `app/src/main/kotlin/com/riffscroll/data/RoutineRepository.kt`

Updated to accept an optional `PersistenceManager`:
- Constructor parameter: `persistenceManager: PersistenceManager? = null`
- Loads persisted data on initialization
- Calls `persistData()` after every data modification
- Backward compatible (tests work without persistence)

**Persistence Points**:
- ✅ `saveRoutine()` - saves after creating routine
- ✅ `deleteSavedRoutine()` - persists after deletion
- ✅ `createSchedule()` - saves after schedule creation
- ✅ `updateSchedule()` - persists after updates
- ✅ `addRoutineToSchedule()` - saves changes
- ✅ `removeRoutineFromSchedule()` - saves changes
- ✅ `deleteSchedule()` - persists deletion
- ✅ `createCalendarSchedule()` - saves new schedule
- ✅ `markCalendarScheduleCompleted()` - persists completion
- ✅ `deleteCalendarSchedule()` - saves deletion
- ✅ `createPracticeSchedulePlan()` - persists new plan
- ✅ `deletePracticeSchedulePlan()` - saves deletion

### 3. PracticeViewModel Enhancement
**File**: `app/src/main/kotlin/com/riffscroll/viewmodel/PracticeViewModel.kt`

Updated to use PersistenceManager:
- Constructor parameter: `persistenceManager: PersistenceManager? = null`
- Loads user progress on initialization
- Saves user progress after completing routines
- Passes PersistenceManager to RoutineRepository

**User Progress Persistence**:
- Loaded in `init {}` block
- Saved in `completeRoutine()` after XP/level updates
- Preserves: level, XP, XP to next level, total practice minutes, completed routines

### 4. ViewModelFactory
**File**: `app/src/main/kotlin/com/riffscroll/viewmodel/PracticeViewModelFactory.kt`

Custom factory to inject PersistenceManager into PracticeViewModel:
- Takes Context to create PersistenceManager
- Follows Android ViewModel factory pattern
- Used in MainActivity to create ViewModel with dependencies

### 5. MainActivity Update
**File**: `app/src/main/kotlin/com/riffscroll/MainActivity.kt`

Updated to use ViewModelFactory:
- Imports `LocalContext` to get Context in Composable
- Uses `PracticeViewModelFactory` to create ViewModel
- PersistenceManager is automatically initialized

### 6. Build Configuration
**File**: `app/build.gradle.kts`

Added Gson dependency:
```kotlin
implementation("com.google.code.gson:gson:2.10.1")
```

### 7. ProGuard Rules
**File**: `app/proguard-rules.pro`

Added rules to prevent Gson classes and data models from being obfuscated:
- Keeps Gson library classes
- Keeps all data model classes in `com.riffscroll.data`
- Preserves annotations and signatures for reflection

### 8. Instrumented Tests
**File**: `app/src/androidTest/kotlin/com/riffscroll/data/PersistenceManagerTest.kt`

Comprehensive test suite for persistence:
- ✅ User progress persistence
- ✅ Saved routines persistence
- ✅ Schedules persistence
- ✅ Calendar schedules persistence
- ✅ Practice schedule plans persistence
- ✅ Clear all data functionality

## How It Works

### Data Flow

1. **App Launch**:
   - MainActivity creates ViewModelFactory with Context
   - ViewModelFactory creates PersistenceManager
   - PracticeViewModel receives PersistenceManager
   - RoutineRepository receives PersistenceManager
   - All persisted data is loaded into memory

2. **During App Use**:
   - User interacts with app (creates routines, completes practice, etc.)
   - Changes are made to in-memory data structures
   - After each modification, data is persisted to SharedPreferences
   - Data remains available even if app is killed

3. **App Restart**:
   - All data is loaded from SharedPreferences
   - User sees their progress, routines, and schedules intact
   - Seamless continuation of their practice journey

### Storage Format

Data is stored as JSON in SharedPreferences:
```
riffscroll_prefs:
  - user_progress: {"level":5,"xp":250,"xpToNextLevel":500,...}
  - saved_routines: [{"id":"saved_123","name":"My Routine",...}]
  - schedules: [{"id":"schedule_456","name":"Weekly",...}]
  - calendar_schedules: [{"id":"cal_789","date":1234567890,...}]
  - practice_schedule_plans: [{"id":"plan_012","name":"Plan",...}]
```

## Benefits

1. **User Data Preservation**: Progress, routines, and schedules survive app restarts
2. **Seamless Experience**: No manual save/load actions required
3. **Performance**: In-memory operations remain fast, persistence happens asynchronously
4. **Backward Compatible**: Existing code and tests work without modification
5. **Testable**: Instrumented tests verify persistence works correctly
6. **Type-Safe**: Gson handles serialization/deserialization automatically
7. **Production Ready**: ProGuard rules ensure release builds work correctly

## Technical Details

### Why SharedPreferences?
- ✅ Simple to implement for current data volume
- ✅ Built-in to Android, no additional dependencies beyond Gson
- ✅ Synchronous API works well with current architecture
- ✅ Appropriate for user preferences and small datasets
- ✅ No migration complexity (can upgrade to Room later if needed)

### Why Gson?
- ✅ Mature, well-tested JSON library
- ✅ Simple API for serialization/deserialization
- ✅ Handles complex nested objects automatically
- ✅ No security vulnerabilities (checked with gh-advisory-database)
- ✅ Works well with Kotlin data classes

### Future Enhancements

If the app grows significantly, consider:
1. **Room Database**: For larger datasets, complex queries, or relationships
2. **DataStore**: Google's modern replacement for SharedPreferences
3. **Cloud Sync**: Backup data to cloud for multi-device access
4. **Practice History**: Store detailed session logs (may need Room for performance)

## Testing

### Unit Tests
Existing unit tests in `RoutineRepositoryTest` continue to work because:
- PersistenceManager parameter is optional (defaults to null)
- Tests run without persistence layer
- All core functionality remains testable

### Instrumented Tests
New tests in `PersistenceManagerTest` verify:
- Data can be saved and loaded correctly
- JSON serialization/deserialization works
- Data survives across PersistenceManager instances
- Clear data functionality works

### Manual Testing
To verify persistence:
1. Open app, generate routine, complete practice session
2. Check user progress (level, XP increased)
3. Force close app (swipe away from recent apps)
4. Reopen app
5. Verify: User progress is preserved, level and XP match

## Summary

This implementation provides robust, production-ready data persistence for RiffScroll using Android best practices. Users' practice progress, routines, and schedules are now safely stored and automatically restored, creating a seamless experience across app sessions.
