package com.sun.moviesun.data.source.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Client {

  fun initializeRetrofit(): MovieService {
    val httpClientBuilder = OkHttpClient.Builder()
        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(RequestInterceptor())
    return Retrofit.Builder()
        .baseUrl(Api.getUrl())
        .client(httpClientBuilder.build())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieService::class.java)
  }

  companion object {
    private const val WRITE_TIMEOUT = 5000L
    private const val READ_TIMEOUT = 5000L
    private const val CONNECT_TIMEOUT = 8000L
    private var sInstance: Client? = null

    @JvmStatic
    fun getInstance(): Client {
      if (sInstance == null) {
        synchronized(Client::javaClass) {
          sInstance = Client()
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
