# Practice Notes and Feedback Feature

## Overview

The Practice Notes and Feedback feature allows users to capture their thoughts, reflections, and impressions during or after practice sessions. This helps build better practice habits, track progress over time, and identify areas that need more focus.

## Key Features

### 1. Practice Notes

**Quick Notes During Practice**

Users can add text notes at any point during a practice session:
- Free-form text entry (up to several lines)
- Optional 1-5 star rating for the exercise or moment
- Timestamp automatically recorded
- Linked to specific exercises
- Multiple notes can be added per session

**Use Cases:**
- "Struggled with the fast picking section - need to slow down tempo"
- "Finally nailed that difficult transition! ⭐⭐⭐⭐⭐"
- "Left hand feels tense - remember to relax wrist"
- "Great tone today on the bends"

### 2. Exercise Feedback

**Structured Feedback After Exercises**

After practicing each exercise, users can provide structured feedback:

**Difficulty Rating:**
- Too Easy
- Just Right
- Challenging  
- Too Hard

**Enjoyment Rating:**
- 1-5 stars with emoji indicators (😞 to 😊)
- Helps identify which exercises are most engaging

**Additional Notes:**
- Optional text field for extra context
- Can explain why something was difficult or enjoyable

**Benefits:**
- Helps tailor future practice routines
- Identifies exercises that need adjustment
- Tracks engagement and motivation
- Provides data for improving exercise selection

### 3. History Integration

**Notes in Practice History**

All notes and feedback are preserved in practice history:
- Expandable sections for each session
- Visual indicators showing sessions with notes
- Easy review of past reflections
- Search through old notes (future enhancement)

## User Interface

### During Practice Session

**Notes & Feedback Card**
Located below the current exercise card and metronome controls:

```
╔════════════════════════════════════╗
║  📝 Notes & Feedback      3 notes  ║
║  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ║
║  [📝 Add Note]  [💭 Feedback]     ║
║                                    ║
║  ✓ Feedback recorded               ║
║    Difficulty: Just Right          ║
║    Enjoyment: ⭐⭐⭐⭐⭐            ║
║                                    ║
║  Recent Notes:                     ║
║  • Great progress today! ⭐⭐⭐⭐   ║
║  • Focus on keeping tempo steady   ║
║  + 1 more notes                    ║
╚════════════════════════════════════╝
```

**Add Note Dialog**
- Text field for note content
- 5-star rating buttons (optional)
- Save/Cancel buttons

**Add Feedback Dialog**
- 4 difficulty rating buttons
- 5-star enjoyment scale
- Optional notes field
- Skip/Save buttons

### In Practice History

**History Entry with Notes**
```
╔═══════════════════════════════════════╗
║  Morning Warmup      +90 XP           ║
║  January 23, 2025 at 9:00 AM          ║
║  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  ║
║  [45 min] [6 exercises] [🎸 Guitar]   ║
║  [Intermediate] [📝 5]                ║
║                                       ║
║  [▶ Show Notes & Feedback]            ║
╚═══════════════════════════════════════╝

(Expanded view)
╔═══════════════════════════════════════╗
║  📝 Practice Notes:                   ║
║  ┌─────────────────────────────────┐  ║
║  │ Finally got the hammer-ons     │  ║
║  │ clean! ⭐⭐⭐⭐⭐               │  ║
║  └─────────────────────────────────┘  ║
║  ┌─────────────────────────────────┐  ║
║  │ Need to work on alternate      │  ║
║  │ picking speed ⭐⭐⭐            │  ║
║  └─────────────────────────────────┘  ║
║                                       ║
║  💭 Exercise Feedback:                ║
║  ┌─────────────────────────────────┐  ║
║  │ Difficulty: Just Right          │  ║
║  │ Enjoyment: ⭐⭐⭐⭐⭐            │  ║
║  │ Perfect warm-up exercise        │  ║
║  └─────────────────────────────────┘  ║
╚═══════════════════════════════════════╝
```

## Technical Implementation

### Data Models

**PracticeNote**
```kotlin
data class PracticeNote(
    val id: String,                    // Unique identifier
    val exerciseId: String,            // Which exercise
    val sessionId: String,             // Which session
    val text: String,                  // Note content
    val timestamp: Long,               // When created
    val rating: Int? = null            // Optional 1-5 rating
)
```

**ExerciseFeedback**
```kotlin
data class ExerciseFeedback(
    val exerciseId: String,            // Which exercise
    val difficulty: DifficultyRating,  // How hard it was
    val enjoyment: Int,                // 1-5 rating
    val notes: String = ""             // Optional notes
)

enum class DifficultyRating {
    TOO_EASY, JUST_RIGHT, CHALLENGING, TOO_HARD
}
```

**Updated Models**
```kotlin
data class PracticeSession(
    // ... existing fields ...
    val sessionId: String,
    val notes: List<PracticeNote> = emptyList(),
    val exerciseFeedback: Map<String, ExerciseFeedback> = emptyMap()
)

data class PracticeHistoryEntry(
    // ... existing fields ...
    val notes: List<PracticeNote> = emptyList(),
    val exerciseFeedback: Map<String, ExerciseFeedback> = emptyMap()
)
```

### ViewModel Methods

**Adding Notes:**
```kotlin
fun addPracticeNote(text: String, rating: Int? = null)
fun getCurrentSessionNotes(): List<PracticeNote>
fun deletePracticeNote(noteId: String)
```

**Adding Feedback:**
```kotlin
fun addExerciseFeedback(
    difficulty: DifficultyRating, 
    enjoyment: Int, 
    notes: String = ""
)
fun getCurrentExerciseFeedback(): ExerciseFeedback?
```

### Persistence

Notes and feedback are automatically persisted:
- Stored with practice history entries
- Saved to SharedPreferences via Gson serialization
- Loaded when app restarts
- No size limits (reasonable use)

## User Workflows

### Workflow 1: Adding Quick Note During Practice

1. User is practicing "Chromatic Scale Exercise"
2. Notices their finger position is improving
3. Clicks "📝 Add Note" button
4. Types: "Left hand position feels much better today"
5. Selects 4-star rating
6. Clicks "Save"
7. Note appears in "Recent Notes" section
8. Note is saved with session and appears in history later

### Workflow 2: Providing Exercise Feedback

1. User completes "Alternate Picking Drill"
2. Clicks "💭 Feedback" button
3. Selects "Challenging" for difficulty
4. Selects 4 stars for enjoyment
5. Types: "Good workout, but need to increase speed gradually"
6. Clicks "Save Feedback"
7. Feedback card shows "✓ Feedback recorded"
8. Can still modify feedback before moving to next exercise
9. Feedback is included in practice history

### Workflow 3: Reviewing Past Notes

1. User opens "Practice History" screen
2. Sees sessions with "📝 5" badge (5 notes)
3. Clicks "▶ Show Notes & Feedback"
4. Reviews all notes and feedback from that session
5. Reflects on progress made since then
6. Can see patterns in difficulty ratings over time

## Benefits

### For Users

**Reflection and Learning:**
- Capture insights in the moment
- Review progress over time
- Identify recurring challenges
- Celebrate improvements

**Better Practice Planning:**
- Know which exercises to repeat or skip
- Adjust difficulty levels appropriately
- Focus on engaging exercises
- Track what works best

**Motivation:**
- See tangible progress through notes
- Remember breakthrough moments
- Build practice confidence
- Create practice journal

### For Future Development

**Data-Driven Features:**
- Recommend exercises based on feedback
- Adjust difficulty automatically
- Identify "favorite" exercises
- Generate personalized routines
- Suggest when to advance to harder exercises

## Privacy and Data

- All notes stored locally on device
- No cloud sync in current version
- User has full control over their data
- Can be backed up via app data backup
- Future: Optional cloud sync with encryption

## Accessibility

- Dialog forms are keyboard-accessible
- Screen reader friendly labels
- High contrast text in notes
- Large touch targets for rating buttons
- Expandable sections reduce clutter

## Future Enhancements

### Short Term
- Edit existing notes
- Delete individual notes
- Search through notes
- Filter history by rating

### Medium Term
- Voice-to-text for notes
- Quick note templates ("Great!", "Need work", etc.)
- Tags for categorizing notes
- Export notes to text file

### Long Term
- Share notes with teachers
- AI analysis of notes for insights
- Trend charts based on feedback
- Community note sharing (optional)
- Video/audio attachments to notes

## Testing

Comprehensive unit tests cover:
- Creating notes with all fields
- Creating feedback with ratings
- Adding notes to sessions
- Storing notes in history
- Rating validation (1-5)
- Multiple notes per session
- Multiple feedback entries per session

## Conclusion

The Practice Notes and Feedback feature transforms RiffScroll from a practice timer into a comprehensive practice journal. By capturing thoughts and impressions during practice, users can build better habits, track meaningful progress, and maintain motivation over the long term.
