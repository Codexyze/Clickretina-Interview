package com.nutrino.clickretina_interview.presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
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
import com.nutrino.clickretina_interview.domain.models.course.CategoryModel
import com.nutrino.clickretina_interview.domain.models.course.CourseDetailModel
import com.nutrino.clickretina_interview.presentation.Navigation.Routes.COURSE_DETAIL_SCREEN
import com.nutrino.clickretina_interview.presentation.States.CourseUIState
import com.nutrino.clickretina_interview.presentation.Viewmodels.CourseViewModel
import com.nutrino.clickretina_interview.presentation.elements.ErrorScreen
import com.nutrino.clickretina_interview.presentation.elements.LoadingScreen

/**
 * The main landing screen of the application.
 * Displays course categories and a list of popular courses with search functionality.
 *
 * @param navController Controller used for navigating between screens.
 * @param viewModel The [CourseViewModel] providing state and data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: CourseViewModel = hiltViewModel()
) {
    val state by viewModel.courseState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCourse()
    }

    Scaffold(
        containerColor = Color(0xFFFDFBF7)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val currentState = state) {
                is CourseUIState.Loading -> LoadingScreen()
                is CourseUIState.Error -> ErrorScreen(message = currentState.message) { viewModel.getCourse() }
                is CourseUIState.Success -> {
                    val allCourses = currentState.data.categories.flatMap { cat -> cat.courses.map { cat.id to it } }
                    val filteredCourses = if (searchQuery.isEmpty()) {
                        allCourses
                    } else {
                        allCourses.filter { (_, course) ->
                            course.title.contains(searchQuery, ignoreCase = true) ||
                                    course.instructor.name.contains(searchQuery, ignoreCase = true)
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp),
                    ) {
                        item {
                            HeaderSection()
                        }

                        item {
                            SearchSection(
                                query = searchQuery,
                                onQueryChange = { viewModel.updateSearchQuery(it) }
                            )
                        }

                        if (searchQuery.isEmpty()) {
                            item {
                                SectionHeader(title = "Categories", onSeeAllClick = {})
                                LazyRow(
                                    contentPadding = PaddingValues(horizontal = 24.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    items(currentState.data.categories) { category ->
                                        CategoryItem(category)
                                    }
                                }
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(24.dp))
                            SectionHeader(
                                title = if (searchQuery.isEmpty()) "Popular courses" else "Search results",
                                onSeeAllClick = {}
                            )
                        }

                        items(filteredCourses) { (catId, course) ->
                            CourseItem(catId, course) {
                                navController.navigate(COURSE_DETAIL_SCREEN(catId, course.id))
                            }
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}

/**
 * Top section of the Home screen displaying a welcome message and user avatar.
 */
@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Welcome back",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = "Find your next skill",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(45.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF008080)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

/**
 * Styled search input field.
 * 
 * @param query Current text in the search field.
 * @param onQueryChange Callback for text changes.
 */
@Composable
fun SearchSection(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        placeholder = { Text("Search courses, topics...", color = Color.LightGray) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color.LightGray) },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedBorderColor = Color(0xFFEEEEEE),
            focusedBorderColor = Color(0xFFEEEEEE),
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black
        ),
        singleLine = true
    )
}

/**
 * A header for sections in the Home screen with a "See all" button.
 * 
 * @param title The title of the section.
 * @param onSeeAllClick Callback for the "See all" action.
 */
@Composable
fun SectionHeader(title: String, onSeeAllClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "See all",
            color = Color(0xFF008080),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable { onSeeAllClick() }
        )
    }
}

/**
 * Horizontal list item representing a course category.
 * 
 * @param category The [CategoryModel] data to display.
 */
@Composable
fun CategoryItem(category: CategoryModel) {
    Surface(
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.width(160.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(android.graphics.Color.parseColor(category.iconColor)).copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(android.graphics.Color.parseColor(category.iconColor)))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = category.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 18.sp,
                modifier = Modifier.height(36.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${category.courseCount} courses",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

/**
 * Vertical list item representing a specific course.
 * 
 * @param categoryId ID of the category this course belongs to.
 * @param course The [CourseDetailModel] data to display.
 * @param onClick Callback for when the course item is clicked.
 */
@Composable
fun CourseItem(categoryId: String, course: CourseDetailModel, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = course.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = course.level.uppercase(),
                    color = Color(0xFFFFA500),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = course.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = course.instructor.name,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⭐ ${course.rating}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = Icons.Default.Search, // Using Search as a placeholder for clock
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${course.durationHours}h", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}