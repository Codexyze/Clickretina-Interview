package com.nutrino.clickretina_interview.presentation.Navigation.Routes

import kotlinx.serialization.Serializable

@Serializable
object HOME_SCREEN

@Serializable
data class COURSE_DETAIL_SCREEN(
    val categoryId: String,
    val courseId: String
)

@Serializable
data class LESSON_SCREEN(
    val categoryId: String,
    val courseId: String,
    val lessonId: String
)