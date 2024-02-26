package com.mbappe.common

sealed class ApiResponse<T> (
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ApiResponse<T>(data = data)
    class Error<T>(errorMessage: String) : ApiResponse<T>(message = errorMessage)
}
