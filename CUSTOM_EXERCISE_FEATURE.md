# Custom Exercise Creation Feature

## Overview
This feature allows users to create, edit, and delete their own custom practice exercises for both guitar and piano. Custom exercises are seamlessly integrated into the existing exercise system and can be used in routine generation.

## User Flow

### Creating a Custom Exercise
1. Navigate to Exercise Browser (from home screen)
2. Tap the "+" button in the top right corner
3. Fill out the exercise form:
   - **Name** (required): Exercise title
   - **Description** (required): Brief description
   - **Category** (required): Technique, Creativity, or Songs
   - **Instrument** (required): Guitar 🎸 or Piano 🎹
   - **Duration** (required): Minutes (must be at least 1)
   - **Difficulty** (required): Beginner, Intermediate, or Advanced
   - **Uses Metronome**: Toggle on if timing is needed
   - **BPM**: If metronome enabled, specify BPM (40-240)
   - **Instructions** (at least 1 required): Up to 6 step-by-step instructions
   - **Tablature/Notation** (optional): Guitar tabs or music notation
4. Tap "Create" to save
5. Exercise appears in Exercise Browser with "✏️ Custom" badge

### Editing a Custom Exercise
1. Navigate to Exercise Browser
2. Find your custom exercise (look for the "✏️ Custom" badge)
3. Tap the pencil icon (✏️) on the exercise card
4. Modify any fields
5. Tap "Save" to update

### Deleting a Custom Exercise
1. Navigate to Exercise Browser
2. Find your custom exercise
3. Tap the trash icon (🗑️) on the exercise card
4. Exercise is permanently deleted

### Using Custom Exercises
- Custom exercises automatically appear in the Exercise Browser
- They can be filtered by instrument, category, and difficulty just like built-in exercises
- When generating a routine, custom exercises are included in the selection pool
- Custom exercises work with the metronome and timer during practice sessions

## Technical Implementation

### Data Model
```kotlin
data class Exercise(
    val id: String,  // UUID-based for custom exercises
    val name: String,
    val description: String,
    val category: ExerciseCategory,
    val instrument: InstrumentType,
    val durationMinutes: Int,
    val difficulty: DifficultyLevel = DifficultyLevel.BEGINNER,
    val hasTiming: Boolean = false,
    val bpm: Int? = null,
    val instructions: List<String> = emptyList(),
    val tablature: String? = null,
    val isCustom: Boolean = false  // NEW: Flag for custom exercises
)
```

### Storage
- Custom exercises are stored in SharedPreferences using JSON serialization
- Key: `"custom_exercises"`
- Persisted on every create/update/delete operation
- Loaded automatically on app startup

### Repository Architecture
```
ExerciseRepository
├── customExercises: MutableList<Exercise>  // User-created exercises
├── techniqueExercises: List<Exercise>      // Built-in exercises
├── creativityExercises: List<Exercise>
├── songExercises: List<Exercise>
└── getAllExercises()  // Merges custom + built-in
```

### CRUD Operations

#### Create
```kotlin
viewModel.addCustomExercise(
    name = "My Custom Exercise",
    description = "Description",
    category = ExerciseCategory.TECHNIQUE,
    instrument = InstrumentType.GUITAR,
    durationMinutes = 10,
    difficulty = DifficultyLevel.INTERMEDIATE,
    hasTiming = true,
    bpm = 120,
    instructions = listOf("Step 1", "Step 2"),
    tablature = "e|--1-2-3--|"
)
```

#### Read
```kotlin
// Get all exercises (includes custom)
val allExercises = viewModel.getAllExercises()

// Get only custom exercises
val customExercises = viewModel.getCustomExercises()

// Get specific exercise by ID
val exercise = viewModel.getExerciseById("custom_uuid")
```

#### Update
```kotlin
val updatedExercise = existingExercise.copy(
    name = "Updated Name",
    durationMinutes = 15
)
viewModel.updateCustomExercise(updatedExercise)
```

#### Delete
```kotlin
viewModel.deleteCustomExercise("custom_uuid")
```

## UI Components

### CustomExerciseScreen
- Full-screen form for creating/editing exercises
- RPG-themed styling matching the app design
- Real-time validation with error messages
- Supports both portrait and landscape orientations

### ExerciseBrowserScreen Enhancements
- "+" button in app bar to create new exercise
- Edit (✏️) and Delete (🗑️) buttons on custom exercise cards
- "✏️ Custom" badge to distinguish user-created exercises
- Built-in exercises show no action buttons (read-only)

## Validation Rules

### Required Fields
- Name: Must not be blank
- Description: Must not be blank
- Duration: Must be at least 1 minute
- Instructions: At least one instruction required

### Conditional Requirements
- BPM: Required if "Uses Metronome" is enabled, must be 40-240

### Optional Fields
- Tablature/Notation: Can be left blank

## Integration with Existing Features

### Routine Generation
- Custom exercises are automatically included in the pool
- Routine generation algorithm treats custom exercises the same as built-in
- Can be filtered by instrument and difficulty

### Exercise Browser
- Custom exercises appear alongside built-in exercises
- All search and filter functionality works
- Sorting applies to both custom and built-in exercises

### Practice Sessions
- Custom exercises work with the timer
- Metronome functions if BPM is specified
- Instructions display during practice
- XP is earned for completing custom exercises

## Testing

### Unit Tests (CustomExerciseTest.kt)
- ✅ Create custom exercise with unique ID
- ✅ Custom exercise included in getAllExercises
- ✅ getCustomExercises returns only custom exercises
- ✅ Update custom exercise
- ✅ Cannot update built-in exercises
- ✅ Delete custom exercise
- ✅ Cannot delete built-in exercises
- ✅ Load custom exercises from storage
- ✅ Filter non-custom exercises during load
- ✅ Find exercises by ID (both custom and built-in)
- ✅ Custom exercises in routine generation
- ✅ Support for all difficulty levels
- ✅ Support for both instruments

## Security Considerations

### Data Storage
- Local storage only (SharedPreferences)
- No network transmission
- Data persists across app restarts
- Cleared when app data is cleared

### ID Generation
- UUID-based to prevent collisions
- Prefixed with "custom_" for easy identification
- No sequential or predictable IDs

### Input Validation
- All user input is validated
- No SQL injection risk (using JSON serialization)
- No script injection risk (text-only storage)

## Future Enhancements

### Potential Features
1. **Exercise Templates**: Pre-filled forms for common exercise types
2. **Import/Export**: Share exercises with other users
3. **Exercise Library**: Online repository of community-created exercises
4. **Categories**: User-defined categories beyond Technique/Creativity/Songs
5. **Video/Audio**: Attach media files to exercises
6. **Difficulty Estimation**: AI-based difficulty suggestion
7. **Exercise Tracking**: Track how often each custom exercise is practiced
8. **Favorites**: Star frequently-used custom exercises
9. **Backup/Restore**: Cloud backup of custom exercises
10. **Collaboration**: Share exercises with friends

## Limitations

### Current Constraints
- Maximum 6 instruction steps (can be expanded if needed)
- Text-only tablature/notation (no rich formatting)
- No media attachments (audio, video, images)
- No undo for deletions (permanent)
- Local storage only (no cloud sync)

## Troubleshooting

### Common Issues

**Q: My custom exercise disappeared**
- A: Check if app data was cleared or app was uninstalled

**Q: Can't edit a built-in exercise**
- A: Built-in exercises are read-only. Create a custom copy instead.

**Q: Custom exercise not appearing in routines**
- A: Check that filters (instrument, difficulty) don't exclude it

**Q: Validation error on BPM**
- A: BPM must be between 40-240 if metronome is enabled

## Conclusion

The custom exercise creation feature provides users with powerful tools to personalize their practice routines. By seamlessly integrating with existing functionality, custom exercises enhance the app's flexibility while maintaining the cohesive RPG-themed experience.
