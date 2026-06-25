package com.nutrino.clickretina_interview.presentation.Viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrino.clickretina_interview.domain.StateHandeling.ResultState
import com.nutrino.clickretina_interview.domain.useCases.course.GetCourseUseCase
import com.nutrino.clickretina_interview.presentation.States.CourseUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the state of course-related screens.
 * Communicates with [GetCourseUseCase] to fetch data and updates the UI state accordingly.
 *
 * @property getCourseUseCase The use case for retrieving courses.
 */
@HiltViewModel
class CourseViewModel @Inject constructor(
    private val getCourseUseCase: GetCourseUseCase
) : ViewModel() {

    private val _courseState = MutableStateFlow<CourseUIState>(CourseUIState.Idle)
    /** Exposes the current [CourseUIState] to the UI. */
    val courseState: StateFlow<CourseUIState> = _courseState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    /** Exposes the current search query string to the UI. */
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    /**
     * Updates the current search query.
     * 
     * @param query The new search string entered by the user.
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    /**
     * Triggers the course fetching process.
     * Updates [courseState] based on the result of the use case.
     */
    fun getCourse() {
        viewModelScope.launch {
            getCourseUseCase().collect { result ->
                when (result) {
                    is ResultState.Loading -> {
                        _courseState.value = CourseUIState.Loading
                    }
                    is ResultState.Success -> {
                        _courseState.value = CourseUIState.Success(result.data)
                    }
                    is ResultState.Error -> {
                        _courseState.value = CourseUIState.Error(result.message)
                    }
                }
            }
        }
    }
}