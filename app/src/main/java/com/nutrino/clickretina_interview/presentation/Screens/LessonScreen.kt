package com.nutrino.clickretina_interview.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nutrino.clickretina_interview.domain.models.course.CourseDetailModel
import com.nutrino.clickretina_interview.domain.models.course.LessonModel
import com.nutrino.clickretina_interview.presentation.States.CourseUIState
import com.nutrino.clickretina_interview.presentation.Viewmodels.CourseViewModel
import com.nutrino.clickretina_interview.presentation.elements.ErrorScreen
import com.nutrino.clickretina_interview.presentation.elements.LoadingScreen

/**
 * Screen for viewing a specific lesson's video and notes.
 *
 * @param categoryId ID of the category.
 * @param courseId ID of the course.
 * @param lessonId ID of the current lesson to display.
 * @param navController Navigation controller.
 * @param viewModel ViewModel for data access.
 */
@Composable
fun LessonScreen(
    categoryId: String,
    courseId: String,
    lessonId: String,
    navController: NavController,
    viewModel: CourseViewModel = hiltViewModel()
) {
    val state by viewModel.courseState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCourse()
    }

    Surface(color = Color(0xFF121212), modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is CourseUIState.Loading -> LoadingScreen()
            is CourseUIState.Error -> ErrorScreen(message = currentState.message) { viewModel.getCourse() }
            is CourseUIState.Success -> {
                val course = currentState.data.categories
                    .find { it.id == categoryId }
                    ?.courses?.find { it.id == courseId }

                val lesson = course?.lessons?.find { it.id == lessonId }

                if (course != null && lesson != null) {
                    LessonContent(course, lesson, navController)
                } else {
                    ErrorScreen(message = "Lesson not found") { navController.popBackStack() }
                }
            }
            else -> Unit
        }
    }
}

/**
 * Main content layout for the Lesson screen.
 * 
 * @param course The current course detail model.
 * @param currentLesson The lesson being played.
 * @param navController Navigation controller.
 */
@Composable
fun LessonContent(course: CourseDetailModel, currentLesson: LessonModel, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Video Player Placeholder
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { /* Play/Pause */ },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.2f))
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
            }

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
            }
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Text(
                        "LESSON ${course.lessons.indexOf(currentLesson) + 1} - ${course.title.uppercase()}",
                        color = Color(0xFF008080),
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(currentLesson.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(currentLesson.content, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }

                item {
                    TabRow(
                        selectedTabIndex = 0,
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF008080),
                        divider = {}
                    ) {
                        Tab(selected = true, onClick = {}, text = { Text("Lessons") })
                        Tab(selected = false, onClick = {}, text = { Text("Notes") })
                        Tab(selected = false, onClick = {}, text = { Text("Resources") })
                    }
                }

                items(course.lessons) { lesson ->
                    val isCurrent = lesson.id == currentLesson.id
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isCurrent) Color(0xFFF0F9F9) else Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        border = if (isCurrent) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
                    ) {
                        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(if (isCurrent) Color(0xFF008080) else Color(0xFFF0F9F9)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    if (isCurrent) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = if (isCurrent) Color.White else Color(0xFF008080),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(lesson.title, fontWeight = FontWeight.SemiBold, color = if (isCurrent) Color(0xFF008080) else Color.Black)
                                Text(if (isCurrent) "Now playing • ${lesson.durationMinutes} min" else "${lesson.durationMinutes} min", fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}