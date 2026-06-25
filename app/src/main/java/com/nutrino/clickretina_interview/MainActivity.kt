package com.nutrino.clickretina_interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nutrino.clickretina_interview.presentation.Navigation.NavHost.SkillforgeApp
import com.nutrino.clickretina_interview.ui.theme.ClickretinaInterviewTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity and entry point into the Android application process.
 * Annotated with [AndroidEntryPoint] to enable Hilt dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Initializes the activity and sets the Jetpack Compose content.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickretinaInterviewTheme {
                SkillforgeApp()
            }
        }
    }
}