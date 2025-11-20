# Saved Routines and Schedules Feature Implementation

## Overview
This implementation adds the ability to save practice routines and organize them into schedules in the RiffScroll guitar practice app.

## Changes Made

### 1. Data Models (`Models.kt`)
Added two new data classes:

```kotlin
data class SavedRoutine(
    val id: String,
    val name: String,
    val routine: PracticeRoutine,
    val createdAt: Long = System.currentTimeMillis()
)

data class Schedule(
    val id: String,
    val name: String,
    val description: String = "",
    val routineIds: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)
```

### 2. Routine Repository (`RoutineRepository.kt`)
New repository class for managing saved routines and schedules:

**Saved Routines Operations:**
- `saveRoutine(name, routine)` - Save a routine with a custom name
- `getSavedRoutines()` - Get all saved routines sorted by creation date
- `getSavedRoutine(id)` - Get a specific saved routine
- `deleteSavedRoutine(id)` - Delete a saved routine (also removes from schedules)

**Schedule Operations:**
- `createSchedule(name, description)` - Create a new schedule
- `getSchedules()` - Get all schedules
- `getSchedule(id)` - Get a specific schedule
- `updateSchedule(schedule)` - Update an existing schedule
- `addRoutineToSchedule(scheduleId, routineId)` - Add a routine to a schedule
- `removeRoutineFromSchedule(scheduleId, routineId)` - Remove a routine from a schedule
- `deleteSchedule(id)` - Delete a schedule
- `getRoutinesInSchedule(scheduleId)` - Get all routines in a schedule

**Note:** Currently uses in-memory storage. Data is lost when the app is closed.

### 3. ViewModel Updates (`PracticeViewModel.kt`)
Extended `PracticeViewModel` with:

**New State Flows:**
- `savedRoutines: StateFlow<List<SavedRoutine>>`
- `schedules: StateFlow<List<Schedule>>`

**New Methods:**
- `saveCurrentRoutine(name)` - Save the current routine
- `loadSavedRoutine(id)` - Load a saved routine as current
- `deleteSavedRoutine(id)` - Delete a saved routine
- `refreshSavedRoutines()` - Refresh the saved routines list
- `createSchedule(name, description)` - Create a new schedule
- `addRoutineToSchedule(scheduleId, routineId)` - Add routine to schedule
- `removeRoutineFromSchedule(scheduleId, routineId)` - Remove routine from schedule
- `deleteSchedule(id)` - Delete a schedule
- `getRoutinesInSchedule(scheduleId)` - Get routines in a schedule
- `refreshSchedules()` - Refresh the schedules list

### 4. UI Updates (`HomeScreen.kt`)

#### Updated HomeScreen Parameters
Added new parameters for saved routines and schedules:
- `savedRoutines: List<SavedRoutine>`
- `schedules: List<Schedule>`
- Multiple callback functions for CRUD operations

#### Updated RoutinePreviewCard
Added optional save functionality:
- Save button alongside Start Practice button
- Dialog for entering routine name
- Only shows save button when `onSaveRoutine` callback is provided

#### New SavedRoutinesSection Component
Displays all saved routines with:
- Expandable list showing routine details
- Load button to use a saved routine
- Delete button to remove a routine
- Exercise count and duration summary

#### New SchedulesSection Component
Manages schedules with:
- Create new schedule button with dialog
- Expandable list showing schedule details and routines
- Add routines to schedule with selection dialog
- Remove routines from schedule
- Delete schedule functionality
- Load routines from within schedules

### 5. MainActivity Updates (`MainActivity.kt`)
Wired up the new functionality:
- Added state collection for `savedRoutines` and `schedules`
- Added `LaunchedEffect` to refresh data on app start
- Passed all new callbacks to `HomeScreen`

### 6. Unit Tests (`RoutineRepositoryTest.kt`)
Comprehensive test coverage for `RoutineRepository`:
- Save and retrieve routines
- Delete routines
- Create schedules
- Add/remove routines from schedules
- Delete schedules
- Cascade delete (removing routine from schedules when deleted)

## User Workflows

### Saving a Routine
1. User generates or has a current routine
2. User clicks "💾 Save" button
3. Dialog appears asking for routine name
4. User enters name and clicks "Save"
5. Routine appears in "Saved Routines" section

### Loading a Saved Routine
1. User finds routine in "Saved Routines" section
2. User clicks play button (▶️)
3. Routine becomes the current routine
4. User can start practice session

### Creating a Schedule
1. User clicks "+" button in "Schedules" section
2. Dialog appears asking for schedule name and description
3. User enters details and clicks "Create"
4. New schedule appears in list

### Adding Routines to Schedule
1. User expands a schedule
2. User clicks "+" button to add routine
3. Dialog shows list of saved routines
4. User selects a routine
5. Routine is added to schedule

### Managing Schedule Routines
1. User expands a schedule to see its routines
2. Each routine shows:
   - Name and details
   - Play button to load and practice
   - Remove button (X) to remove from schedule

## Technical Notes

### Design Decisions
- **In-Memory Storage**: Chosen for simplicity and minimal changes
- **Repository Pattern**: Separates data management from ViewModel
- **Expandable UI**: Reduces clutter while providing full functionality
- **Dialog-Based Input**: Familiar pattern for Android users
- **Immutable Data**: Follows Kotlin best practices

### Future Enhancements
- Add persistent storage (Room database or DataStore)
- Add routine editing functionality
- Add schedule reordering
- Add routine templates
- Add import/export functionality
- Add routine sharing
- Add practice history tracking

## Testing
Unit tests cover all repository operations. Manual testing requires:
1. Android device or emulator
2. Build and install the app
3. Test workflows described above

## Code Quality
- All code follows existing Kotlin conventions
- Uses existing RPG-themed components
- Minimal changes to existing code
- Clear separation of concerns
- Comprehensive documentation
