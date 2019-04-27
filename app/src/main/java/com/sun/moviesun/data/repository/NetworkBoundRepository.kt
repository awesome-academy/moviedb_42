package com.sun.moviesun.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.sun.moviesun.data.model.Resource
import com.sun.moviesun.data.model.network.NetworkResponse
import com.sun.moviesun.data.repository.mapper.NetworkResponseMapper
import com.sun.moviesun.data.source.remote.ApiResponse
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundRepository<
    ResultType, RequestType : NetworkResponse,
    Mapper : NetworkResponseMapper<RequestType>>
@MainThread internal constructor() {

  private val asFlowable: Flowable<Resource<ResultType>>

  init {
    val source = this.loadFromRemote()
        .subscribeOn(Schedulers.io())
        .doOnNext {apiResponse ->
          cacheData(processResponse(apiResponse)!!)
          loadFromLocal().publish().map { resultType ->
            Resource.success(resultType, mapper().onLastPage(apiResponse.body!!))
          }
        }
        .flatMap { loadFromLocal().publish().map { Resource.success(it, false) } }
        .doOnError { onGetFailure(it.message) }
        .onErrorResumeNext { throwable: Throwable ->
          loadFromLocal().publish().map { resultType ->
            if (shouldGetData(resultType)) {
              Resource.success(resultType, false)
            } else {
              Resource.error(throwable.message!!, resultType)
            }
          }
        }
        .observeOn(AndroidSchedulers.mainThread())
    asFlowable = Flowable.concat(
        this.loadFromLocal()
            .publish()
            .map { Resource.loading(it) }
            .take(DEFAULT_TAKE_COUNT), source)
  }

  fun asFlowable(): Flowable<Resource<ResultType>> {
    return asFlowable
  }

  @WorkerThread
  protected fun processResponse(response: ApiResponse<RequestType>): RequestType? {
    return response.body
  }

  @WorkerThread
  protected abstract fun cacheData(items: RequestType)

  @MainThread
  protected abstract fun shouldGetData(data: ResultType?): Boolean

  @MainThread
  protected abstract fun loadFromLocal(): Flowable<ResultType>

  @MainThread
  protected abstract fun loadFromRemote(): Flowable<ApiResponse<RequestType>>

  @MainThread
  protected abstract fun mapper(): Mapper

  @MainThread
  protected abstract fun onGetFailure(message: String?)

  companion object {
    private const val DEFAULT_TAKE_COUNT = 1L
  }
}
