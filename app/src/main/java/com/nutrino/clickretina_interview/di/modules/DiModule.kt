package com.nutrino.clickretina_interview.di.modules

import com.nutrino.clickretina_interview.data.RepoImpl.course.CourseRepoImpl
import com.nutrino.clickretina_interview.data.remote.ApiService
import com.nutrino.clickretina_interview.domain.Repository.CourseRepository
import com.nutrino.clickretina_interview.domain.useCases.course.GetCourseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt Module for providing core application dependencies such as Retrofit, ApiService, and Repositories.
 */
@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    /** Provides a singleton instance of [Retrofit]. */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/android-assesment/notes/refs/heads/main/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Provides a singleton instance of [ApiService]. */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /** Provides a singleton instance of [CourseRepository]. */
    @Provides
    @Singleton
    fun provideCourseRepository(apiService: ApiService): CourseRepository {
        return CourseRepoImpl(apiService)
    }

    /** Provides a singleton instance of [GetCourseUseCase]. */
    @Provides
    @Singleton
    fun provideGetCourseUseCase(repository: CourseRepository): GetCourseUseCase {
        return GetCourseUseCase(repository)
    }
}