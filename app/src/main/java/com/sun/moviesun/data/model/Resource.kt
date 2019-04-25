package com.sun.moviesun.data.model

import com.google.gson.Gson
import com.sun.moviesun.data.source.remote.ErrorResponse
import com.sun.moviesun.data.source.remote.StatusCode
import com.sun.moviesun.data.source.remote.StatusKey

class Resource<out T>(
    val status: Int,
    val data: T?,
    val message: String?,
    val onLastPage: Boolean
) {
  var errorResponse: ErrorResponse? = null

  init {
    message?.let {
      errorResponse = try {
        Gson().fromJson(message, ErrorResponse::class.java)
      } catch (e: Exception) {
        ErrorResponse(StatusCode.NOT_FOUND, message, false)
      }
    }
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val resource = other as Resource<*>
    if (status != resource.status) return false
    if (if (message != null) message != resource.message else resource.message != null) return false
    return if (data != null) data == resource.data else resource.data == null
  }

  override fun toString() = "Resource[status= $status,message= $message,data= $data]"

  override fun hashCode() = 0

  companion object {

    fun <T> success(data: T?, onLastPage: Boolean): Resource<T> =
        Resource(status = StatusKey.SUCCESS, data = data, message = null, onLastPage = onLastPage)

    fun <T> error(msg: String, data: T?): Resource<T> =
        Resource(status = StatusKey.ERROR, data = data, message = msg, onLastPage = true)

    fun <T> loading(data: T?): Resource<T> =
        Resource(status = StatusKey.LOADING, data = data, message = null, onLastPage = false)
  }
}
