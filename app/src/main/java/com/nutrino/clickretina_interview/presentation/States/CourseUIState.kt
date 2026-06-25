package com.nutrino.clickretina_interview.presentation.States

import com.nutrino.clickretina_interview.domain.models.course.CourseModel

/**
 * Sealed class representing the different states of the Course screen UI.
 */
sealed class CourseUIState {
    /** Represents the initial state before any data is loaded. */
    object Idle : CourseUIState()
    
    /** Represents the state while course data is being fetched. */
    object Loading : CourseUIState()
    
    /** 
     * Represents a successful data load.
     * @property data The loaded [CourseModel].
     */
    data class Success(val data: CourseModel) : CourseUIState()
    
    /** 
     * Represents a failed data load.
     * @property message The error message to be displayed.
     */
    data class Error(val message: String) : CourseUIState()
}