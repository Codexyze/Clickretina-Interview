package com.nutrino.clickretina_interview.data.RepoImpl.course

import com.nutrino.clickretina_interview.data.mappers.course.toDomain
import com.nutrino.clickretina_interview.data.remote.ApiService
import com.nutrino.clickretina_interview.domain.Repository.CourseRepository
import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.models.course.CourseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CourseRepoImpl @Inject constructor(
    private val apiService: ApiService
) : CourseRepository {
    override suspend fun getCourse(): Flow<ResultState<CourseModel>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getCourse()
            emit(ResultState.Success(response.toDomain()))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "Unknown error"))
        }
    }
}