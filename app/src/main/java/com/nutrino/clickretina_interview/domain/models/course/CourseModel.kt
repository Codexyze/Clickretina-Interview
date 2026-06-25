package com.nutrino.clickretina_interview.domain.models.course

import kotlinx.serialization.Serializable

@Serializable
data class CourseModel(
    val categories: List<CategoryModel>,
    val meta: MetaModel?
)

@Serializable
data class CategoryModel(
    val id: String,
    val name: String,
    val description: String,
    val iconColor: String,
    val courseCount: Int,
    val courses: List<CourseDetailModel>
)

@Serializable
data class CourseDetailModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val thumbnailUrl: String,
    val level: String,
    val durationHours: Double,
    val rating: Double,
    val studentsEnrolled: Int,
    val language: String,
    val lastUpdated: String,
    val tags: List<String>,
    val instructor: InstructorModel,
    val description: String,
    val lessons: List<LessonModel>
)

@Serializable
data class InstructorModel(
    val id: String,
    val name: String,
    val title: String,
    val avatarUrl: String,
    val bio: String
)

@Serializable
data class LessonModel(
    val id: String,
    val title: String,
    val durationMinutes: Int,
    val isFree: Boolean,
    val videoUrl: String,
    val content: String
)

@Serializable
data class MetaModel(
    val app: String,
    val version: String,
    val generatedAt: String
)