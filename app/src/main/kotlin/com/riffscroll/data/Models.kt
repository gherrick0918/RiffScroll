package com.riffscroll.data

/**
 * Type of musical instrument
 */
enum class InstrumentType(val displayName: String, val emoji: String) {
    GUITAR("Guitar", "🎸"),
    PIANO("Piano", "🎹")
}

/**
 * Category of practice exercises
 */
enum class ExerciseCategory {
    TECHNIQUE,
    CREATIVITY,
    SONGS
}

/**
 * Difficulty level for exercises
 */
enum class DifficultyLevel(val displayName: String, val level: Int) {
    BEGINNER("Beginner", 1),
    INTERMEDIATE("Intermediate", 2),
    ADVANCED("Advanced", 3)
}

/**
 * Represents a single practice exercise for an instrument
 */
data class Exercise(
    val id: String,
    val name: String,
    val description: String,
    val category: ExerciseCategory,
    val instrument: InstrumentType,
    val durationMinutes: Int,
    val difficulty: DifficultyLevel = DifficultyLevel.BEGINNER,
    val hasTiming: Boolean = false,
    val bpm: Int? = null,
    val instructions: List<String> = emptyList(),
    val tablature: String? = null,  // Optional tablature/notation
    val isCustom: Boolean = false  // Flag to identify user-created exercises
)

/**
 * Represents a practice routine with multiple exercises
 */
data class PracticeRoutine(
    val id: String,
    val exercises: List<Exercise>,
    val totalDurationMinutes: Int,
    val difficulty: Int = 1
)

/**
 * Tracks the progress of a practice session
 */
data class PracticeSession(
    val routine: PracticeRoutine,
    val currentExerciseIndex: Int = 0,
    val isActive: Boolean = false,
    val isPaused: Boolean = false,
    val elapsedSeconds: Int = 0,
    val sessionId: String = "",  // Unique ID for this session
    val notes: List<PracticeNote> = emptyList(),  // Notes added during session
    val exerciseFeedback: Map<String, ExerciseFeedback> = emptyMap()  // Feedback per exercise
)

/**
 * User progress and stats (RPG elements)
 */
data class UserProgress(
    val level: Int = 1,
    val xp: Int = 0,
    val xpToNextLevel: Int = 100,
    val totalPracticeMinutes: Int = 0,
    val completedRoutines: Int = 0
)

/**
 * Represents a saved practice routine
 */
data class SavedRoutine(
    val id: String,
    val name: String,
    val routine: PracticeRoutine,
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Represents a schedule that groups multiple saved routines
 */
data class Schedule(
    val id: String,
    val name: String,
    val description: String = "",
    val routineIds: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Represents a calendar-based schedule entry for a specific date
 */
data class CalendarSchedule(
    val id: String,
    val date: Long,  // Date in milliseconds since epoch
    val routineId: String,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Represents an auto-generated practice schedule for multiple days
 */
data class PracticeSchedulePlan(
    val id: String,
    val name: String,
    val startDate: Long,
    val endDate: Long,
    val scheduleEntries: List<CalendarSchedule> = emptyList(),
    val instrument: InstrumentType? = null,  // null means both instruments
    val targetDurationMinutes: Int = 45,
    val difficulty: DifficultyLevel? = null,
    val daysPerWeek: Int = 7,  // Number of practice days per week (1-7)
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Represents a completed practice session in history
 */
data class PracticeHistoryEntry(
    val id: String,
    val completedAt: Long,  // Timestamp when session was completed
    val durationMinutes: Int,  // Actual duration of the session
    val xpEarned: Int,  // XP gained from the session
    val routineName: String,  // Name of the routine practiced
    val exerciseCount: Int,  // Number of exercises completed
    val instrument: InstrumentType?,  // Primary instrument used (null if both)
    val difficulty: DifficultyLevel?,  // Difficulty level of the routine
    val notes: List<PracticeNote> = emptyList(),  // Notes from the session
    val exerciseFeedback: Map<String, ExerciseFeedback> = emptyMap()  // Feedback per exercise
)

/**
 * Calculated statistics from practice history
 */
data class PracticeStatistics(
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,  // Days with consecutive practice
    val longestStreak: Int = 0,  // Longest streak ever achieved
    val averageSessionMinutes: Int = 0,
    val favoriteInstrument: InstrumentType? = null,  // Most practiced instrument
    val sessionsThisWeek: Int = 0,
    val sessionsThisMonth: Int = 0,
    val lastPracticeDate: Long? = null  // Timestamp of last practice session
)

/**
 * Represents a note taken during or after practicing an exercise
 */
data class PracticeNote(
    val id: String,
    val exerciseId: String,  // ID of the exercise this note is about
    val sessionId: String,  // ID of the practice session
    val text: String,  // The note content
    val timestamp: Long = System.currentTimeMillis(),  // When the note was created
    val rating: Int? = null  // Optional 1-5 star rating for the exercise
)

/**
 * Represents feedback or reflection on an exercise or session
 */
data class ExerciseFeedback(
    val exerciseId: String,
    val difficulty: DifficultyRating,  // How difficult was it?
    val enjoyment: Int,  // 1-5 rating of how enjoyable
    val notes: String = ""  // Optional additional notes
)

/**
 * Rating for exercise difficulty (user's subjective experience)
 */
enum class DifficultyRating(val displayName: String) {
    TOO_EASY("Too Easy"),
    JUST_RIGHT("Just Right"),
    CHALLENGING("Challenging"),
    TOO_HARD("Too Hard")
}
