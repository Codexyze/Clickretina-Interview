package com.nutrino.clickretina_interview.data.remote.apiResponse


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("courseCount")
    val courseCount: Int?,
    @SerializedName("courses")
    val courses: List<Course?>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("iconColor")
    val iconColor: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)