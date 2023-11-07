package com.atomic.io.sportwidget.data.presentation

/**
 * A sealed class representing different states of data presentation.
 *
 * This sealed class is used to model the state of data in a structured way and is typically
 * employed in scenarios where you want to handle different data states like initial, loading,
 * success, or error in a type-safe manner.
 *
 * @param T The type of data presentation, which can vary based on the state.
 */
sealed class DataPresentation<out T> {
    object Initial: DataPresentation<Nothing>()
    object Loading: DataPresentation<Nothing>()
    data class Success<T>(val presentation: T) : DataPresentation<T>()
    data class Error(val error: Throwable) : DataPresentation<Nothing>()
}
