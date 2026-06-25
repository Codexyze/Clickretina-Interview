package com.nutrino.clickretina_interview.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nutrino.clickretina_interview.domain.models.course.CourseDetailModel
import com.nutrino.clickretina_interview.domain.models.course.LessonModel
import com.nutrino.clickretina_interview.presentation.Navigation.Routes.LESSON_SCREEN
import com.nutrino.clickretina_interview.presentation.States.CourseUIState
import com.nutrino.clickretina_interview.presentation.Viewmodels.CourseViewModel
import com.nutrino.clickretina_interview.presentation.elements.ErrorScreen
import com.nutrino.clickretina_interview.presentation.elements.LoadingScreen

/**
 * Screen displaying detailed information about a selected course.
 * Shows instructor details, course description, and a list of lessons.
 *
 * @param categoryId ID of the category the course belongs to.
 * @param courseId ID of the specific course to display.
 * @param navController Controller for app navigation.
 * @param viewModel ViewModel providing course data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(
    categoryId: String,
    courseId: String,
    navController: NavController,
    viewModel: CourseViewModel = hiltViewModel()
) {
    val state by viewModel.courseState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCourse()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Course Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFDFBF7))
            )
        },
        containerColor = Color(0xFFFDFBF7)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val currentState = state) {
                is CourseUIState.Loading -> LoadingScreen()
                is CourseUIState.Error -> ErrorScreen(message = currentState.message) { viewModel.getCourse() }
                is CourseUIState.Success -> {
                    val course = currentState.data.categories
                        .find { it.id == categoryId }
                        ?.courses?.find { it.id == courseId }

                    if (course != null) {
                        CourseDetailContent(course) { lessonId ->
                            navController.navigate(LESSON_SCREEN(categoryId, courseId, lessonId))
                        }
                    } else {
                        ErrorScreen(message = "Course not found") { navController.popBackStack() }
                    }
                }
                else -> Unit
            }
        }
    }
}

/**
 * The primary scrollable content of the Course Detail screen.
 * 
 * @param course The [CourseDetailModel] data to display.
 * @param onLessonClicked Callback for navigating to a specific lesson.
 */
@Composable
fun CourseDetailContent(course: CourseDetailModel, onLessonClicked: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            AsyncImage(
                model = course.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(course.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text(course.subtitle, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("⭐ ${course.rating}", fontWeight = FontWeight.Bold, color = Color(0xFFFFB800))
                Spacer(modifier = Modifier.width(12.dp))
                Text("${course.studentsEnrolled} students", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(12.dp))
                Text(course.level, color = Color(0xFF008080), fontWeight = FontWeight.SemiBold)
            }
        }

        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = course.instructor.avatarUrl,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp).clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(course.instructor.name, fontWeight = FontWeight.Bold)
                        Text(course.instructor.title, fontSize = 12.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Follow", color = Color(0xFF008080), fontWeight = FontWeight.Bold, modifier = Modifier.clickable { })
                }
            }
        }

        item {
            Text("Course Content", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        items(course.lessons) { lesson ->
            LessonItem(lesson) { onLessonClicked(lesson.id) }
        }
    }
}

/**
 * List item representing a single lesson in the course detail view.
 * 
 * @param lesson The [LessonModel] data.
 * @param onClick Callback for clicking the lesson.
 */
@Composable
fun LessonItem(lesson: LessonModel, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        modifier = Modifier.fillMaxWidth(),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFF0F9F9)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFF008080), modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(lesson.title, fontWeight = FontWeight.SemiBold)
                Text("${lesson.durationMinutes} min", fontSize = 12.sp, color = Color.Gray)
            }
            if (lesson.isFree) {
                Surface(
                    color = Color(0xFFE0F2F2),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text("FREE", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF008080))
                }
            }
        }
    }
}