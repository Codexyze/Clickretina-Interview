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

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val getCourseUseCase: GetCourseUseCase
) : ViewModel() {

    private val _courseState = MutableStateFlow<CourseUIState>(CourseUIState.Idle)
    val courseState: StateFlow<CourseUIState> = _courseState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }


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