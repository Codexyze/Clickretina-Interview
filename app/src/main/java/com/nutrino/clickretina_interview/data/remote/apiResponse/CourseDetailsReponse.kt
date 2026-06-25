package com.nutrino.clickretina_interview.data.remote.apiResponse


import com.google.gson.annotations.SerializedName

data class CourseDetailsReponse(
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("meta")
    val meta: Meta?
)