package com.nutrino.clickretina_interview.presentation.Navigation.NavHost

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nutrino.clickretina_interview.presentation.Navigation.Routes.COURSE_DETAIL_SCREEN
import com.nutrino.clickretina_interview.presentation.Navigation.Routes.HOME_SCREEN
import com.nutrino.clickretina_interview.presentation.Navigation.Routes.LESSON_SCREEN
import com.nutrino.clickretina_interview.presentation.Screens.CourseDetailScreen
import com.nutrino.clickretina_interview.presentation.Screens.HomeScreen
import com.nutrino.clickretina_interview.presentation.Screens.LessonScreen

/**
 * The main application entry point that sets up the navigation graph.
 * Uses type-safe navigation to handle transitions between Home, Course Detail, and Lesson screens.
 */
@Composable
fun SkillforgeApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HOME_SCREEN) {
        composable<HOME_SCREEN> {
            HomeScreen(navController = navController)
        }
        composable<COURSE_DETAIL_SCREEN> { backStackEntry ->
            val args = backStackEntry.toRoute<COURSE_DETAIL_SCREEN>()
            CourseDetailScreen(
                categoryId = args.categoryId,
                courseId = args.courseId,
                navController = navController
            )
        }
        composable<LESSON_SCREEN> { backStackEntry ->
            val args = backStackEntry.toRoute<LESSON_SCREEN>()
            LessonScreen(
                categoryId = args.categoryId,
                courseId = args.courseId,
                lessonId = args.lessonId,
                navController = navController
            )
        }
    }
}