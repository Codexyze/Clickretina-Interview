package com.nutrino.clickretina_interview.domain.Repository

import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.models.course.CourseModel
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourse(): Flow<ResultState<CourseModel>>
}