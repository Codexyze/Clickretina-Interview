package com.nutrino.clickretina_interview.domain.models.course

import kotlinx.serialization.Serializable

/**
 * Root domain model for course data.
 *
 * @property categories List of available course categories.
 * @property meta Metadata related to the course data source.
 */
@Serializable
data class CourseModel(
    val categories: List<CategoryModel>,
    val meta: MetaModel?
)

/**
 * Domain model representing a category of courses.
 *
 * @property id Unique identifier for the category.
 * @property name Display name of the category.
 * @property description Brief description of the category.
 * @property iconColor Hex color string for the category icon background.
 * @property courseCount Total number of courses in this category.
 * @property courses List of courses belonging to this category.
 */
@Serializable
data class CategoryModel(
    val id: String,
    val name: String,
    val description: String,
    val iconColor: String,
    val courseCount: Int,
    val courses: List<CourseDetailModel>
)

/**
 * Domain model representing detailed information about a specific course.
 *
 * @property id Unique identifier for the course.
 * @property title The main title of the course.
 * @property subtitle A short secondary title or tagline.
 * @property thumbnailUrl URL for the course preview image.
 * @property level Difficulty level (e.g., Beginner, Intermediate).
 * @property durationHours Total length of the course in hours.
 * @property rating Average user rating of the course.
 * @property studentsEnrolled Number of students currently enrolled.
 * @property language Primary language of instruction.
 * @property lastUpdated Timestamp of the last course update.
 * @property tags List of keywords associated with the course.
 * @property instructor Information about the person teaching the course.
 * @property description Detailed full-text description of the course.
 * @property lessons List of individual lessons within the course.
 */
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

/**
 * Domain model representing a course instructor.
 *
 * @property id Unique identifier for the instructor.
 * @property name Full name of the instructor.
 * @property title Professional title or designation.
 * @property avatarUrl URL for the instructor's profile picture.
 * @property bio Short professional biography.
 */
@Serializable
data class InstructorModel(
    val id: String,
    val name: String,
    val title: String,
    val avatarUrl: String,
    val bio: String
)

/**
 * Domain model representing a single lesson within a course.
 *
 * @property id Unique identifier for the lesson.
 * @property title The title of the lesson.
 * @property durationMinutes Length of the lesson in minutes.
 * @property isFree Whether the lesson is available for preview without enrollment.
 * @property videoUrl URL to the lesson's video content.
 * @property content Textual or HTML content/notes for the lesson.
 */
@Serializable
data class LessonModel(
    val id: String,
    val title: String,
    val durationMinutes: Int,
    val isFree: Boolean,
    val videoUrl: String,
    val content: String
)

/**
 * Domain model representing source metadata.
 *
 * @property app Name of the application providing the data.
 * @property version Version of the data schema.
 * @property generatedAt Timestamp when the data was generated.
 */
@Serializable
data class MetaModel(
    val app: String,
    val version: String,
    val generatedAt: String
)