package com.sun.moviesun.data.source.remote

import com.sun.moviesun.data.model.network.MovieResponse
import com.sun.moviesun.data.source.MovieDataSource
import io.reactivex.Flowable

class MovieRemoteDataSource private constructor(
    client: Client
): MovieDataSource.Remote {

  private var requestService : MovieService = client.initializeRetrofit()

  override fun getMoviesTrendingByDay(): Flowable<ApiResponse<MovieResponse>> = requestService.getMoviesTrendingByDay()

  override fun getMoviesCategory(category: String?, page: Int): Flowable<ApiResponse<MovieResponse>> =
      requestService.getMoviesCategory(category, page)

  companion object {
    private var sInstance: MovieRemoteDataSource? = null

    @JvmStatic
    fun getInstance(client: Client): MovieRemoteDataSource {
      if (sInstance == null) {
        synchronized(MovieRemoteDataSource::javaClass) {
          sInstance = MovieRemoteDataSource(client)
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
