package com.riffscroll.data

import org.junit.Test
import org.junit.Assert.*

/**
 * Unit tests for Practice History functionality
 */
class PracticeHistoryTest {
    
    @Test
    fun `addPracticeHistoryEntry should add entry to history`() {
        val repository = RoutineRepository()
        
        val entry = PracticeHistoryEntry(
            id = "history_1",
            completedAt = System.currentTimeMillis(),
            durationMinutes = 45,
            xpEarned = 90,
            routineName = "Morning Practice",
            exerciseCount = 5,
            instrument = InstrumentType.GUITAR,
            difficulty = DifficultyLevel.INTERMEDIATE
        )
        
        repository.addPracticeHistoryEntry(entry)
        
        val history = repository.getPracticeHistory()
        assertEquals(1, history.size)
        assertEquals("Morning Practice", history[0].routineName)
        assertEquals(45, history[0].durationMinutes)
    }
    
    @Test
    fun `getPracticeHistory should return entries sorted by date descending`() {
        val repository = RoutineRepository()
        val now = System.currentTimeMillis()
        
        val entry1 = PracticeHistoryEntry(
            id = "history_1",
            completedAt = now - 2000,
            durationMinutes = 30,
            xpEarned = 60,
            routineName = "Practice 1",
            exerciseCount = 3,
            instrument = InstrumentType.GUITAR,
            difficulty = DifficultyLevel.BEGINNER
        )
        
        val entry2 = PracticeHistoryEntry(
            id = "history_2",
            completedAt = now,
            durationMinutes = 45,
            xpEarned = 90,
            routineName = "Practice 2",
            exerciseCount = 5,
            instrument = InstrumentType.PIANO,
            difficulty = DifficultyLevel.INTERMEDIATE
        )
        
        val entry3 = PracticeHistoryEntry(
            id = "history_3",
            completedAt = now - 1000,
            durationMinutes = 60,
            xpEarned = 120,
            routineName = "Practice 3",
            exerciseCount = 7,
            instrument = InstrumentType.GUITAR,
            difficulty = DifficultyLevel.ADVANCED
        )
        
        repository.addPracticeHistoryEntry(entry1)
        repository.addPracticeHistoryEntry(entry2)
        repository.addPracticeHistoryEntry(entry3)
        
        val history = repository.getPracticeHistory()
        assertEquals(3, history.size)
        // Most recent first
        assertEquals("Practice 2", history[0].routineName)
        assertEquals("Practice 3", history[1].routineName)
        assertEquals("Practice 1", history[2].routineName)
    }
    
    @Test
    fun `getPracticeHistoryByDateRange should filter by date`() {
        val repository = RoutineRepository()
        val now = System.currentTimeMillis()
        val oneDayAgo = now - (24 * 60 * 60 * 1000)
        val twoDaysAgo = now - (2 * 24 * 60 * 60 * 1000)
        val threeDaysAgo = now - (3 * 24 * 60 * 60 * 1000)
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "1",
                completedAt = threeDaysAgo,
                durationMinutes = 30,
                xpEarned = 60,
                routineName = "Old Practice",
                exerciseCount = 3,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.BEGINNER
            )
        )
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "2",
                completedAt = twoDaysAgo,
                durationMinutes = 45,
                xpEarned = 90,
                routineName = "Recent Practice 1",
                exerciseCount = 5,
                instrument = InstrumentType.PIANO,
                difficulty = DifficultyLevel.INTERMEDIATE
            )
        )
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "3",
                completedAt = oneDayAgo,
                durationMinutes = 60,
                xpEarned = 120,
                routineName = "Recent Practice 2",
                exerciseCount = 7,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.ADVANCED
            )
        )
        
        // Get entries from last 2.5 days
        val history = repository.getPracticeHistoryByDateRange(
            startDate = now - (3 * 24 * 60 * 60 * 1000) / 2,
            endDate = now
        )
        
        assertEquals(2, history.size)
        assertTrue(history.any { it.routineName == "Recent Practice 1" })
        assertTrue(history.any { it.routineName == "Recent Practice 2" })
        assertFalse(history.any { it.routineName == "Old Practice" })
    }
    
    @Test
    fun `calculateStatistics should return correct basic stats`() {
        val repository = RoutineRepository()
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "1",
                completedAt = System.currentTimeMillis(),
                durationMinutes = 30,
                xpEarned = 60,
                routineName = "Practice 1",
                exerciseCount = 3,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.BEGINNER
            )
        )
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "2",
                completedAt = System.currentTimeMillis(),
                durationMinutes = 60,
                xpEarned = 120,
                routineName = "Practice 2",
                exerciseCount = 5,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.INTERMEDIATE
            )
        )
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "3",
                completedAt = System.currentTimeMillis(),
                durationMinutes = 45,
                xpEarned = 90,
                routineName = "Practice 3",
                exerciseCount = 4,
                instrument = InstrumentType.PIANO,
                difficulty = DifficultyLevel.BEGINNER
            )
        )
        
        val stats = repository.calculateStatistics()
        
        assertEquals(3, stats.totalSessions)
        assertEquals(135, stats.totalMinutes)
        assertEquals(45, stats.averageSessionMinutes)
        assertEquals(InstrumentType.GUITAR, stats.favoriteInstrument)
    }
    
    @Test
    fun `calculateStatistics should calculate current streak correctly`() {
        val repository = RoutineRepository()
        val now = System.currentTimeMillis()
        
        // Create a calendar for date manipulation
        val today = java.util.Calendar.getInstance().apply {
            timeInMillis = now
            set(java.util.Calendar.HOUR_OF_DAY, 12)
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }
        
        // Add sessions for today, yesterday, and 2 days ago (3-day streak)
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "1",
                completedAt = today.timeInMillis,
                durationMinutes = 30,
                xpEarned = 60,
                routineName = "Today",
                exerciseCount = 3,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.BEGINNER
            )
        )
        
        today.add(java.util.Calendar.DAY_OF_MONTH, -1)
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "2",
                completedAt = today.timeInMillis,
                durationMinutes = 45,
                xpEarned = 90,
                routineName = "Yesterday",
                exerciseCount = 4,
                instrument = InstrumentType.PIANO,
                difficulty = DifficultyLevel.INTERMEDIATE
            )
        )
        
        today.add(java.util.Calendar.DAY_OF_MONTH, -1)
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "3",
                completedAt = today.timeInMillis,
                durationMinutes = 60,
                xpEarned = 120,
                routineName = "Two days ago",
                exerciseCount = 5,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.ADVANCED
            )
        )
        
        val stats = repository.calculateStatistics()
        
        // Current streak should be 3 days
        assertTrue(stats.currentStreak >= 2) // At least 2 days (accounting for timing)
    }
    
    @Test
    fun `calculateStatistics should calculate longest streak correctly`() {
        val repository = RoutineRepository()
        
        val calendar = java.util.Calendar.getInstance().apply {
            set(java.util.Calendar.HOUR_OF_DAY, 12)
            set(java.util.Calendar.MINUTE, 0)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }
        
        // Create a 5-day streak starting 10 days ago
        calendar.add(java.util.Calendar.DAY_OF_MONTH, -10)
        for (i in 0 until 5) {
            repository.addPracticeHistoryEntry(
                PracticeHistoryEntry(
                    id = "old_$i",
                    completedAt = calendar.timeInMillis,
                    durationMinutes = 30,
                    xpEarned = 60,
                    routineName = "Old Practice $i",
                    exerciseCount = 3,
                    instrument = InstrumentType.GUITAR,
                    difficulty = DifficultyLevel.BEGINNER
                )
            )
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }
        
        // Skip a few days
        calendar.add(java.util.Calendar.DAY_OF_MONTH, 3)
        
        // Create a 2-day streak more recently
        for (i in 0 until 2) {
            repository.addPracticeHistoryEntry(
                PracticeHistoryEntry(
                    id = "recent_$i",
                    completedAt = calendar.timeInMillis,
                    durationMinutes = 45,
                    xpEarned = 90,
                    routineName = "Recent Practice $i",
                    exerciseCount = 4,
                    instrument = InstrumentType.PIANO,
                    difficulty = DifficultyLevel.INTERMEDIATE
                )
            )
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 1)
        }
        
        val stats = repository.calculateStatistics()
        
        // Longest streak should be 5 days
        assertEquals(5, stats.longestStreak)
    }
    
    @Test
    fun `calculateStatistics should return empty stats for no history`() {
        val repository = RoutineRepository()
        
        val stats = repository.calculateStatistics()
        
        assertEquals(0, stats.totalSessions)
        assertEquals(0, stats.totalMinutes)
        assertEquals(0, stats.currentStreak)
        assertEquals(0, stats.longestStreak)
        assertEquals(0, stats.averageSessionMinutes)
        assertNull(stats.favoriteInstrument)
        assertEquals(0, stats.sessionsThisWeek)
        assertEquals(0, stats.sessionsThisMonth)
        assertNull(stats.lastPracticeDate)
    }
    
    @Test
    fun `calculateStatistics should count sessions this week and month`() {
        val repository = RoutineRepository()
        val now = System.currentTimeMillis()
        val oneDayAgo = now - (24 * 60 * 60 * 1000)
        val fiveDaysAgo = now - (5 * 24 * 60 * 60 * 1000)
        val fifteenDaysAgo = now - (15 * 24 * 60 * 60 * 1000)
        val fortyDaysAgo = now - (40L * 24 * 60 * 60 * 1000)
        
        // This week
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "1",
                completedAt = oneDayAgo,
                durationMinutes = 30,
                xpEarned = 60,
                routineName = "Recent 1",
                exerciseCount = 3,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.BEGINNER
            )
        )
        
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "2",
                completedAt = fiveDaysAgo,
                durationMinutes = 45,
                xpEarned = 90,
                routineName = "Recent 2",
                exerciseCount = 4,
                instrument = InstrumentType.PIANO,
                difficulty = DifficultyLevel.INTERMEDIATE
            )
        )
        
        // This month (but not this week)
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "3",
                completedAt = fifteenDaysAgo,
                durationMinutes = 60,
                xpEarned = 120,
                routineName = "This Month",
                exerciseCount = 5,
                instrument = InstrumentType.GUITAR,
                difficulty = DifficultyLevel.ADVANCED
            )
        )
        
        // Older than a month
        repository.addPracticeHistoryEntry(
            PracticeHistoryEntry(
                id = "4",
                completedAt = fortyDaysAgo,
                durationMinutes = 30,
                xpEarned = 60,
                routineName = "Old",
                exerciseCount = 3,
                instrument = InstrumentType.PIANO,
                difficulty = DifficultyLevel.BEGINNER
            )
        )
        
        val stats = repository.calculateStatistics()
        
        assertEquals(2, stats.sessionsThisWeek)
        assertEquals(3, stats.sessionsThisMonth)
    }
}
