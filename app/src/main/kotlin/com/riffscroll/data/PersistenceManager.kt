package com.riffscroll.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Manages data persistence using SharedPreferences
 * Handles saving and loading of user progress, routines, and schedules
 */
class PersistenceManager(context: Context) {
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    
    companion object {
        private const val PREFS_NAME = "riffscroll_prefs"
        
        // Keys for different data types
        private const val KEY_USER_PROGRESS = "user_progress"
        private const val KEY_SAVED_ROUTINES = "saved_routines"
        private const val KEY_SCHEDULES = "schedules"
        private const val KEY_CALENDAR_SCHEDULES = "calendar_schedules"
        private const val KEY_PRACTICE_SCHEDULE_PLANS = "practice_schedule_plans"
        private const val KEY_PRACTICE_HISTORY = "practice_history"
    }
    
    // User Progress
    
    fun saveUserProgress(progress: UserProgress) {
        val json = gson.toJson(progress)
        sharedPreferences.edit().putString(KEY_USER_PROGRESS, json).apply()
    }
    
    fun loadUserProgress(): UserProgress? {
        val json = sharedPreferences.getString(KEY_USER_PROGRESS, null) ?: return null
        return try {
            gson.fromJson(json, UserProgress::class.java)
        } catch (e: Exception) {
            null
        }
    }
    
    // Saved Routines
    
    fun saveSavedRoutines(routines: List<SavedRoutine>) {
        val json = gson.toJson(routines)
        sharedPreferences.edit().putString(KEY_SAVED_ROUTINES, json).apply()
    }
    
    fun loadSavedRoutines(): List<SavedRoutine> {
        val json = sharedPreferences.getString(KEY_SAVED_ROUTINES, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<SavedRoutine>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // Schedules
    
    fun saveSchedules(schedules: List<Schedule>) {
        val json = gson.toJson(schedules)
        sharedPreferences.edit().putString(KEY_SCHEDULES, json).apply()
    }
    
    fun loadSchedules(): List<Schedule> {
        val json = sharedPreferences.getString(KEY_SCHEDULES, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<Schedule>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // Calendar Schedules
    
    fun saveCalendarSchedules(schedules: List<CalendarSchedule>) {
        val json = gson.toJson(schedules)
        sharedPreferences.edit().putString(KEY_CALENDAR_SCHEDULES, json).apply()
    }
    
    fun loadCalendarSchedules(): List<CalendarSchedule> {
        val json = sharedPreferences.getString(KEY_CALENDAR_SCHEDULES, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<CalendarSchedule>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // Practice Schedule Plans
    
    fun savePracticeSchedulePlans(plans: List<PracticeSchedulePlan>) {
        val json = gson.toJson(plans)
        sharedPreferences.edit().putString(KEY_PRACTICE_SCHEDULE_PLANS, json).apply()
    }
    
    fun loadPracticeSchedulePlans(): List<PracticeSchedulePlan> {
        val json = sharedPreferences.getString(KEY_PRACTICE_SCHEDULE_PLANS, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<PracticeSchedulePlan>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // Practice History
    
    fun savePracticeHistory(history: List<PracticeHistoryEntry>) {
        val json = gson.toJson(history)
        sharedPreferences.edit().putString(KEY_PRACTICE_HISTORY, json).apply()
    }
    
    fun loadPracticeHistory(): List<PracticeHistoryEntry> {
        val json = sharedPreferences.getString(KEY_PRACTICE_HISTORY, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<PracticeHistoryEntry>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    // Clear all data (useful for testing or user data reset)
    
    fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }
}
