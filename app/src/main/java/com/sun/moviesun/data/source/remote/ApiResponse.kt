package com.sun.moviesun.data.source.remote

import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {
  val code: Int
  val body: T?
  val message: String?

  val isSuccessful: Boolean
    get() = code in StatusCode.OK..StatusCode.FOUND
  val isFailure: Boolean

  constructor(error: Throwable) {
    this.code = StatusCode.NOT_FOUND
    this.body = null
    this.message = error.message
    this.isFailure = true
  }

  constructor(response: Response<T>) {
    this.code = response.code()

    if (response.isSuccessful) {
      this.body = response.body()
      this.message = null
      this.isFailure = false
    } else {
      var errorMessage: String? = null
      response.errorBody()?.let {
        try {
          errorMessage = response.errorBody()!!.string()
        } catch (ignored: IOException) { }
      }
      errorMessage?.apply {
        if (isNullOrEmpty() || trim { it <= ' ' }.isEmpty()) {
          errorMessage = response.message()
        }
      }
      this.body = null
      this.message = errorMessage
      this.isFailure = true
    }
  }
}
