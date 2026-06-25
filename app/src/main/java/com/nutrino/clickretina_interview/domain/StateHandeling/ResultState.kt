package com.nutrino.clickretina_interview.domain.StateHandeling

/**
 * A sealed class representing the different states of a data operation.
 * Used to wrap data and communicate the status (Loading, Success, or Error) to the UI layer.
 *
 * @param T The type of data being handled in the success state.
 */
sealed class ResultState<out T> {
    /** Represents an ongoing operation. */
    object Loading : ResultState<Nothing>()
    
    /**
     * Represents a successful operation.
     * @property data The result data of the operation.
     */
    data class Success<out T>(val data: T) : ResultState<T>()
    
    /**
     * Represents a failed operation.
     * @property message A descriptive error message.
     */
    data class Error(val message: String) : ResultState<Nothing>()
}