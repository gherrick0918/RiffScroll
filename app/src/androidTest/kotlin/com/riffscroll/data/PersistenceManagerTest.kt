package com.riffscroll.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Instrumented tests for PersistenceManager
 * These tests run on an Android device/emulator
 */
@RunWith(AndroidJUnit4::class)
class PersistenceManagerTest {
    
    private lateinit var persistenceManager: PersistenceManager
    private lateinit var context: Context
    
    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        persistenceManager = PersistenceManager(context)
        // Clear any existing data
        persistenceManager.clearAllData()
    }
    
    @After
    fun tearDown() {
        persistenceManager.clearAllData()
    }
    
    @Test
    fun testUserProgressPersistence() {
        // Create and save user progress
        val progress = UserProgress(
            level = 5,
            xp = 250,
            xpToNextLevel = 500,
            totalPracticeMinutes = 120,
            completedRoutines = 10
        )
        
        persistenceManager.saveUserProgress(progress)
        
        // Load and verify
        val loaded = persistenceManager.loadUserProgress()
        assertNotNull(loaded)
        assertEquals(5, loaded?.level)
        assertEquals(250, loaded?.xp)
        assertEquals(500, loaded?.xpToNextLevel)
        assertEquals(120, loaded?.totalPracticeMinutes)
        assertEquals(10, loaded?.completedRoutines)
    }
    
    @Test
    fun testSavedRoutinesPersistence() {
        val exerciseRepo = ExerciseRepository()
        val routine = exerciseRepo.generateBalancedRoutine(45)
        
        val savedRoutine = SavedRoutine(
            id = "test_routine_1",
            name = "Test Routine",
            routine = routine
        )
        
        persistenceManager.saveSavedRoutines(listOf(savedRoutine))
        
        val loaded = persistenceManager.loadSavedRoutines()
        assertEquals(1, loaded.size)
        assertEquals("test_routine_1", loaded[0].id)
        assertEquals("Test Routine", loaded[0].name)
        assertEquals(routine.id, loaded[0].routine.id)
    }
    
    @Test
    fun testSchedulesPersistence() {
        val schedule = Schedule(
            id = "schedule_1",
            name = "Weekly Plan",
            description = "Practice schedule for the week",
            routineIds = listOf("routine_1", "routine_2")
        )
        
        persistenceManager.saveSchedules(listOf(schedule))
        
        val loaded = persistenceManager.loadSchedules()
        assertEquals(1, loaded.size)
        assertEquals("schedule_1", loaded[0].id)
        assertEquals("Weekly Plan", loaded[0].name)
        assertEquals(2, loaded[0].routineIds.size)
    }
    
    @Test
    fun testCalendarSchedulesPersistence() {
        val now = System.currentTimeMillis()
        val calendarSchedule = CalendarSchedule(
            id = "cal_1",
            date = now,
            routineId = "routine_1",
            isCompleted = true
        )
        
        persistenceManager.saveCalendarSchedules(listOf(calendarSchedule))
        
        val loaded = persistenceManager.loadCalendarSchedules()
        assertEquals(1, loaded.size)
        assertEquals("cal_1", loaded[0].id)
        assertEquals(now, loaded[0].date)
        assertEquals("routine_1", loaded[0].routineId)
        assertTrue(loaded[0].isCompleted)
    }
    
    @Test
    fun testPracticeSchedulePlansPersistence() {
        val now = System.currentTimeMillis()
        val plan = PracticeSchedulePlan(
            id = "plan_1",
            name = "Two Week Plan",
            startDate = now,
            endDate = now + (14 * 24 * 60 * 60 * 1000L),
            instrument = InstrumentType.GUITAR,
            targetDurationMinutes = 45,
            difficulty = DifficultyLevel.INTERMEDIATE,
            daysPerWeek = 5
        )
        
        persistenceManager.savePracticeSchedulePlans(listOf(plan))
        
        val loaded = persistenceManager.loadPracticeSchedulePlans()
        assertEquals(1, loaded.size)
        assertEquals("plan_1", loaded[0].id)
        assertEquals("Two Week Plan", loaded[0].name)
        assertEquals(InstrumentType.GUITAR, loaded[0].instrument)
        assertEquals(45, loaded[0].targetDurationMinutes)
        assertEquals(5, loaded[0].daysPerWeek)
    }
    
    @Test
    fun testClearAllData() {
        // Save some data
        val progress = UserProgress(level = 3, xp = 150)
        persistenceManager.saveUserProgress(progress)
        
        // Verify it's saved
        assertNotNull(persistenceManager.loadUserProgress())
        
        // Clear all data
        persistenceManager.clearAllData()
        
        // Verify it's cleared
        assertNull(persistenceManager.loadUserProgress())
        assertTrue(persistenceManager.loadSavedRoutines().isEmpty())
        assertTrue(persistenceManager.loadSchedules().isEmpty())
        assertTrue(persistenceManager.loadCalendarSchedules().isEmpty())
        assertTrue(persistenceManager.loadPracticeSchedulePlans().isEmpty())
    }
}
