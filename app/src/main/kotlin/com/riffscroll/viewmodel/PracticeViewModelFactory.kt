package com.riffscroll.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riffscroll.data.PersistenceManager

/**
 * Factory for creating PracticeViewModel with dependencies
 */
class PracticeViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PracticeViewModel::class.java)) {
            return PracticeViewModel(PersistenceManager(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
