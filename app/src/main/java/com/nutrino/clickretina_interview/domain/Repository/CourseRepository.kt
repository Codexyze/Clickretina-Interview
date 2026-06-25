package com.nutrino.clickretina_interview.domain.Repository

import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.models.course.CourseModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the operations for fetching course-related data.
 * Acts as a contract for the Data layer to implement.
 */
interface CourseRepository {
    /**
     * Fetches the complete list of courses and categories.
     * 
     * @return A [Flow] emitting [ResultState] which wraps the [CourseModel].
     */
    suspend fun getCourse(): Flow<ResultState<CourseModel>>
}