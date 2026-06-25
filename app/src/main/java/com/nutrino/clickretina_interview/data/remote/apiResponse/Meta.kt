package com.nutrino.clickretina_interview.data.remote.apiResponse


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("app")
    val app: String?,
    @SerializedName("generatedAt")
    val generatedAt: String?,
    @SerializedName("version")
    val version: String?
)