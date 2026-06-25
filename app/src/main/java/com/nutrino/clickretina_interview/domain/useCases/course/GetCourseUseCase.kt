package com.nutrino.clickretina_interview.domain.useCases.course

import com.nutrino.clickretina_interview.domain.Repository.CourseRepository
import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.models.course.CourseModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCourseUseCase @Inject constructor(
    private val repository: CourseRepository
) {
    suspend operator fun invoke(): Flow<ResultState<CourseModel>> {
        return repository.getCourse()
    }
}