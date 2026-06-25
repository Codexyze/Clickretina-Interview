package com.nutrino.clickretina_interview.domain.useCases.course

import com.nutrino.clickretina_interview.domain.Repository.CourseRepository
import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.models.course.CourseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving courses from the repository.
 * Encapsulates the business logic for fetching course data.
 *
 * @property repository The [CourseRepository] used to fetch data.
 */
class GetCourseUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    /**
     * Executes the use case to fetch courses.
     * 
     * @return A [Flow] of [ResultState] containing the [CourseModel].
     */
    suspend operator fun invoke(): Flow<ResultState<CourseModel>> {
        return repository.getCourse()
    }
}