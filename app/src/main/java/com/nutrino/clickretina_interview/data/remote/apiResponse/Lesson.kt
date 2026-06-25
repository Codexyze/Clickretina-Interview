package com.nutrino.clickretina_interview.data.remote.apiResponse


import com.google.gson.annotations.SerializedName

data class Lesson(
    @SerializedName("content")
    val content: String?,
    @SerializedName("durationMinutes")
    val durationMinutes: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFree")
    val isFree: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("videoUrl")
    val videoUrl: String?
)