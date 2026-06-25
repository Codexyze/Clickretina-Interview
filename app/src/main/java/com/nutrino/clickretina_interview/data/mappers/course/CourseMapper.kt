package com.nutrino.clickretina_interview.data.mappers.course

import com.nutrino.clickretina_interview.data.remote.apiResponse.*
import com.nutrino.clickretina_interview.domain.models.course.*

fun CourseDetailsReponse.toDomain(): CourseModel {
    return CourseModel(
        categories = this.categories?.mapNotNull { it?.toDomain() } ?: emptyList(),
        meta = this.meta?.toDomain()
    )
}

fun Category.toDomain(): CategoryModel {
    return CategoryModel(
        id = this.id ?: "",
        name = this.name ?: "",
        description = this.description ?: "",
        iconColor = this.iconColor ?: "",
        courseCount = this.courseCount ?: 0,
        courses = this.courses?.mapNotNull { it?.toDomain() } ?: emptyList()
    )
}

fun Course.toDomain(): CourseDetailModel {
    return CourseDetailModel(
        id = this.id ?: "",
        title = this.title ?: "",
        subtitle = this.subtitle ?: "",
        thumbnailUrl = this.thumbnailUrl ?: "",
        level = this.level ?: "",
        durationHours = this.durationHours ?: 0.0,
        rating = this.rating ?: 0.0,
        studentsEnrolled = this.studentsEnrolled ?: 0,
        language = this.language ?: "",
        lastUpdated = this.lastUpdated ?: "",
        tags = this.tags?.mapNotNull { it } ?: emptyList(),
        instructor = this.instructor?.toDomain() ?: InstructorModel("", "", "", "", ""),
        description = this.description ?: "",
        lessons = this.lessons?.mapNotNull { it?.toDomain() } ?: emptyList()
    )
}

fun Instructor.toDomain(): InstructorModel {
    return InstructorModel(
        id = this.id ?: "",
        name = this.name ?: "",
        title = this.title ?: "",
        avatarUrl = this.avatarUrl ?: "",
        bio = this.bio ?: ""
    )
}

fun Lesson.toDomain(): LessonModel {
    return LessonModel(
        id = this.id ?: "",
        title = this.title ?: "",
        durationMinutes = this.durationMinutes ?: 0,
        isFree = this.isFree ?: false,
        videoUrl = this.videoUrl ?: "",
        content = this.content ?: ""
    )
}

fun Meta.toDomain(): MetaModel {
    return MetaModel(
        app = this.app ?: "",
        version = this.version ?: "",
        generatedAt = this.generatedAt ?: ""
    )
}