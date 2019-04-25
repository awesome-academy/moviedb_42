package com.sun.moviesun.data.source.remote

import androidx.annotation.IntDef
import com.sun.moviesun.data.source.remote.StatusKey.ERROR
import com.sun.moviesun.data.source.remote.StatusKey.LOADING
import com.sun.moviesun.data.source.remote.StatusKey.SUCCESS

object StatusCode {
  const val OK = 200
  const val CREATED = 201
  const val NO_CONTENT = 204
  const val FOUND = 302
  const val BAD_REQUEST = 400
  const val UNAUTHORIZED = 401
  const val NOT_FOUND = 404
  const val INTERNAL_SERVER_ERROR = 404
}

object StatusKey {
  const val ERROR = -1
  const val SUCCESS = 0
  const val LOADING = 1
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(ERROR, SUCCESS, LOADING)
annotation class Status
