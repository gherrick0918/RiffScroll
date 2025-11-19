package com.riffscroll.data

/**
 * Category of guitar practice exercises
 */
enum class ExerciseCategory {
    TECHNIQUE,
    CREATIVITY,
    SONGS
}

/**
 * Represents a single guitar exercise
 */
data class Exercise(
    val id: String,
    val name: String,
    val description: String,
    val category: ExerciseCategory,
    val durationMinutes: Int,
    val hasTiming: Boolean = false,
    val bpm: Int? = null,
    val instructions: List<String> = emptyList()
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
