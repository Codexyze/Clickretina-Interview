package com.nutrino.clickretina_interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nutrino.clickretina_interview.presentation.Navigation.NavHost.SkillforgeApp
import com.nutrino.clickretina_interview.ui.theme.ClickretinaInterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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