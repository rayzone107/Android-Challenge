package com.rachit.challenge.util

import androidx.annotation.StringRes


/**
 * Generic class for fetching server/local response and parsing it as success or error.
 *
 * Sealed class for easier realtime type conversion
 */
sealed class Response<T>(
    val data: T? = null,
    @StringRes
    val message: Int? = null
) {
    class Success<T>(data: T) : Response<T>(data)
    class Error<T>(@StringRes message: Int, data: T? = null) : Response<T>(data, message)
}
