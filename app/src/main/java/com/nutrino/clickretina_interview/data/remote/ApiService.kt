package com.nutrino.clickretina_interview.data.remote

import com.nutrino.clickretina_interview.data.remote.apiResponse.CourseDetailsReponse
import retrofit2.http.GET

interface ApiService {
    @GET("data.json")
    suspend fun getCourse(): CourseDetailsReponse
}