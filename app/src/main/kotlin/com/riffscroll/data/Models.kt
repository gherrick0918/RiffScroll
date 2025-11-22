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
    val tablature: String? = null  // Optional tablature/notation
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
    val elapsedSeconds: Int = 0
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
