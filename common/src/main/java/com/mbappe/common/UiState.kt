package com.mbappe.common

sealed class UiState<out T> (
    val data: T? = null,
    val message: String? = null
) {
    data object Loading : UiState<Nothing>()
    class Success<T>(data: T) : UiState<T>(data = data)
    class Error(errorMessage: String) : UiState<Nothing>(message = errorMessage)
}
