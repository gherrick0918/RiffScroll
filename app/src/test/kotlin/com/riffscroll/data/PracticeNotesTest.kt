package com.riffscroll.data

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for practice notes and feedback functionality
 */
class PracticeNotesTest {
    
    @Test
    fun `PracticeNote should be created with all fields`() {
        val note = PracticeNote(
            id = "note1",
            exerciseId = "ex1",
            sessionId = "session1",
            text = "Great practice session!",
            timestamp = 1234567890L,
            rating = 5
        )
        
        assertEquals("note1", note.id)
        assertEquals("ex1", note.exerciseId)
        assertEquals("session1", note.sessionId)
        assertEquals("Great practice session!", note.text)
        assertEquals(1234567890L, note.timestamp)
        assertEquals(5, note.rating)
    }
    
    @Test
    fun `PracticeNote should work without optional rating`() {
        val note = PracticeNote(
            id = "note1",
            exerciseId = "ex1",
            sessionId = "session1",
            text = "Good progress",
            timestamp = 1234567890L,
            rating = null
        )
        
        assertNull(note.rating)
    }
    
    @Test
    fun `ExerciseFeedback should be created with all fields`() {
        val feedback = ExerciseFeedback(
            exerciseId = "ex1",
            difficulty = DifficultyRating.JUST_RIGHT,
            enjoyment = 4,
            notes = "Really enjoyed this one!"
        )
        
        assertEquals("ex1", feedback.exerciseId)
        assertEquals(DifficultyRating.JUST_RIGHT, feedback.difficulty)
        assertEquals(4, feedback.enjoyment)
        assertEquals("Really enjoyed this one!", feedback.notes)
    }
    
    @Test
    fun `DifficultyRating should have correct display names`() {
        assertEquals("Too Easy", DifficultyRating.TOO_EASY.displayName)
        assertEquals("Just Right", DifficultyRating.JUST_RIGHT.displayName)
        assertEquals("Challenging", DifficultyRating.CHALLENGING.displayName)
        assertEquals("Too Hard", DifficultyRating.TOO_HARD.displayName)
    }
    
    @Test
    fun `PracticeSession should store notes and feedback`() {
        val routine = PracticeRoutine(
            id = "routine1",
            exercises = emptyList(),
            totalDurationMinutes = 30
        )
        
        val note1 = PracticeNote(
            id = "note1",
            exerciseId = "ex1",
            sessionId = "session1",
            text = "First note",
            timestamp = 1234567890L
        )
        
        val note2 = PracticeNote(
            id = "note2",
            exerciseId = "ex2",
            sessionId = "session1",
            text = "Second note",
            timestamp = 1234567891L
        )
        
        val feedback = ExerciseFeedback(
            exerciseId = "ex1",
            difficulty = DifficultyRating.CHALLENGING,
            enjoyment = 3
        )
        
        val session = PracticeSession(
            routine = routine,
            sessionId = "session1",
            notes = listOf(note1, note2),
            exerciseFeedback = mapOf("ex1" to feedback)
        )
        
        assertEquals(2, session.notes.size)
        assertEquals("First note", session.notes[0].text)
        assertEquals("Second note", session.notes[1].text)
        assertEquals(1, session.exerciseFeedback.size)
        assertEquals(DifficultyRating.CHALLENGING, session.exerciseFeedback["ex1"]?.difficulty)
    }
    
    @Test
    fun `PracticeHistoryEntry should store notes and feedback`() {
        val note = PracticeNote(
            id = "note1",
            exerciseId = "ex1",
            sessionId = "session1",
            text = "Great session",
            timestamp = 1234567890L,
            rating = 5
        )
        
        val feedback = ExerciseFeedback(
            exerciseId = "ex1",
            difficulty = DifficultyRating.JUST_RIGHT,
            enjoyment = 5,
            notes = "Perfect difficulty level"
        )
        
        val historyEntry = PracticeHistoryEntry(
            id = "history1",
            completedAt = 1234567890L,
            durationMinutes = 45,
            xpEarned = 90,
            routineName = "Morning Routine",
            exerciseCount = 6,
            instrument = InstrumentType.GUITAR,
            difficulty = DifficultyLevel.INTERMEDIATE,
            notes = listOf(note),
            exerciseFeedback = mapOf("ex1" to feedback)
        )
        
        assertEquals(1, historyEntry.notes.size)
        assertEquals("Great session", historyEntry.notes[0].text)
        assertEquals(1, historyEntry.exerciseFeedback.size)
        assertEquals(DifficultyRating.JUST_RIGHT, historyEntry.exerciseFeedback["ex1"]?.difficulty)
    }
    
    @Test
    fun `Rating should be valid between 1 and 5`() {
        val validRatings = listOf(1, 2, 3, 4, 5)
        
        validRatings.forEach { rating ->
            val note = PracticeNote(
                id = "note$rating",
                exerciseId = "ex1",
                sessionId = "session1",
                text = "Note with rating $rating",
                timestamp = System.currentTimeMillis(),
                rating = rating
            )
            assertTrue(note.rating!! in 1..5)
        }
    }
    
    @Test
    fun `Enjoyment rating should be valid between 1 and 5`() {
        val validRatings = listOf(1, 2, 3, 4, 5)
        
        validRatings.forEach { rating ->
            val feedback = ExerciseFeedback(
                exerciseId = "ex1",
                difficulty = DifficultyRating.JUST_RIGHT,
                enjoyment = rating
            )
            assertTrue(feedback.enjoyment in 1..5)
        }
    }
    
    @Test
    fun `Multiple notes can be added to a session`() {
        val routine = PracticeRoutine(
            id = "routine1",
            exercises = emptyList(),
            totalDurationMinutes = 30
        )
        
        val notes = (1..5).map { i ->
            PracticeNote(
                id = "note$i",
                exerciseId = "ex$i",
                sessionId = "session1",
                text = "Note $i",
                timestamp = System.currentTimeMillis() + i
            )
        }
        
        val session = PracticeSession(
            routine = routine,
            sessionId = "session1",
            notes = notes
        )
        
        assertEquals(5, session.notes.size)
        assertEquals("Note 1", session.notes[0].text)
        assertEquals("Note 5", session.notes[4].text)
    }
    
    @Test
    fun `Multiple feedback entries can be stored per session`() {
        val routine = PracticeRoutine(
            id = "routine1",
            exercises = emptyList(),
            totalDurationMinutes = 30
        )
        
        val feedbackMap = mapOf(
            "ex1" to ExerciseFeedback("ex1", DifficultyRating.TOO_EASY, 5),
            "ex2" to ExerciseFeedback("ex2", DifficultyRating.JUST_RIGHT, 4),
            "ex3" to ExerciseFeedback("ex3", DifficultyRating.CHALLENGING, 3)
        )
        
        val session = PracticeSession(
            routine = routine,
            sessionId = "session1",
            exerciseFeedback = feedbackMap
        )
        
        assertEquals(3, session.exerciseFeedback.size)
        assertEquals(DifficultyRating.TOO_EASY, session.exerciseFeedback["ex1"]?.difficulty)
        assertEquals(DifficultyRating.JUST_RIGHT, session.exerciseFeedback["ex2"]?.difficulty)
        assertEquals(DifficultyRating.CHALLENGING, session.exerciseFeedback["ex3"]?.difficulty)
    }
}
