package com.nutrino.clickretina_interview.data.remote.apiResponse


import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("description")
    val description: String?,
    @SerializedName("durationHours")
    val durationHours: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("instructor")
    val instructor: Instructor?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("lastUpdated")
    val lastUpdated: String?,
    @SerializedName("lessons")
    val lessons: List<Lesson?>?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("rating")
    val rating: Double?,
    @SerializedName("studentsEnrolled")
    val studentsEnrolled: Int?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?
)