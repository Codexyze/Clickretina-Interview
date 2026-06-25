package com.nutrino.clickretina_interview.data.RepoImpl.course

import com.nutrino.clickretina_interview.data.mappers.course.toDomain
import com.nutrino.clickretina_interview.data.remote.ApiService
import com.nutrino.clickretina_interview.domain.Repository.CourseRepository
import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.models.course.CourseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of [CourseRepository] that fetches data from the [ApiService].
 * Responsible for handling network calls and converting raw data to domain entities.
 *
 * @property apiService The Retrofit service used for network communication.
 */
class CourseRepoImpl @Inject constructor(
    private val apiService: ApiService
) : CourseRepository {
    /**
     * Fetches courses from the API and maps the response to a domain model.
     * Emits [ResultState.Loading] before starting the network call,
     * followed by [ResultState.Success] or [ResultState.Error].
     * 
     * @return A [Flow] of [ResultState] containing the [CourseModel].
     */
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