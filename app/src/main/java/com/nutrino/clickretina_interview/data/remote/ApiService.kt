package com.nutrino.clickretina_interview.data.remote

import com.nutrino.clickretina_interview.data.remote.apiResponse.CourseDetailsReponse
import retrofit2.http.GET

/**
 * Retrofit service interface defining the API endpoints for the Skillforge application.
 */
interface ApiService {
    /**
     * Fetches the raw course data from the remote source.
     * 
     * @return [CourseDetailsReponse] containing nested categories and courses.
     */
    @GET("data.json")
    suspend fun getCourse(): CourseDetailsReponse
}