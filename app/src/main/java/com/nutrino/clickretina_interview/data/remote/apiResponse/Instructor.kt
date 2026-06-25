package com.nutrino.clickretina_interview.data.remote.apiResponse


import com.google.gson.annotations.SerializedName

data class Instructor(
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?
)