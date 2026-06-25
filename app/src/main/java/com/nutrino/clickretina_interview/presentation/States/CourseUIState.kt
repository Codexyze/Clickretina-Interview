package com.nutrino.clickretina_interview.presentation.States

import com.nutrino.clickretina_interview.domain.models.course.CourseModel

sealed class CourseUIState {
    object Idle : CourseUIState()
    object Loading : CourseUIState()
    data class Success(val data: CourseModel) : CourseUIState()
    data class Error(val message: String) : CourseUIState()
}