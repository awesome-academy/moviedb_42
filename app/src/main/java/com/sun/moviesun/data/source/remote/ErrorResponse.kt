package com.sun.moviesun.data.source.remote

data class ErrorResponse(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
)
