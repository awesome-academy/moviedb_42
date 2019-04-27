package com.sun.moviesun.data.repository

import android.util.Log
import com.sun.moviesun.data.model.Resource
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.data.model.network.MovieResponse
import com.sun.moviesun.data.repository.mapper.MovieResponseMapper
import com.sun.moviesun.data.source.local.MovieLocalDataSource
import com.sun.moviesun.data.source.remote.ApiResponse
import com.sun.moviesun.data.source.remote.CategoryKey
import com.sun.moviesun.data.source.remote.CategoryKeyDef
import com.sun.moviesun.data.source.remote.MovieRemoteDataSource
import io.reactivex.Flowable

class MovieRepository constructor(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) {

  fun getMoviesTrendingByDay(): Flowable<Resource<List<Movie>>> =
      object : NetworkBoundRepository<List<Movie>, MovieResponse, MovieResponseMapper>() {
        override fun cacheData(items: MovieResponse) {
          for (item in items.results) {
            item.page = DEFAULT_PAGE
            item.category = CategoryKeyDef.TRENDING
          }
          local.insertMovie(movies = items.results)
        }

        override fun shouldGetData(data: List<Movie>?): Boolean {
          return data == null || data.isEmpty()
        }

        override fun loadFromLocal(): Flowable<List<Movie>> {
          return local.getMovies(DEFAULT_PAGE, CategoryKeyDef.TRENDING)
        }

        override fun loadFromRemote(): Flowable<ApiResponse<MovieResponse>> {
          return remote.getMoviesTrendingByDay()
        }

        override fun mapper(): MovieResponseMapper {
          return MovieResponseMapper()
        }

        override fun onGetFailure(message: String?) {
          Log.e(TAG, message)
        }
      }.asFlowable()

  fun getMoviesCategory(@CategoryKey category: String, page: Int): Flowable<Resource<List<Movie>>> =
      object : NetworkBoundRepository<List<Movie>, MovieResponse, MovieResponseMapper>() {
        override fun cacheData(items: MovieResponse) {
          for (item in items.results) {
            item.page = page
            item.category = category
          }
          local.insertMovie(movies = items.results)
        }

        override fun shouldGetData(data: List<Movie>?): Boolean {
          return data == null || data.isEmpty()
        }

        override fun loadFromLocal(): Flowable<List<Movie>> {
          return local.getMovies(page, category)
        }

        override fun loadFromRemote(): Flowable<ApiResponse<MovieResponse>> {
          return remote.getMoviesCategory(category, page)
        }

        override fun mapper(): MovieResponseMapper {
          return MovieResponseMapper()
        }

        override fun onGetFailure(message: String?) {
          Log.e(TAG, message)
        }
      }.asFlowable()

  companion object {
    private const val TAG = "MovieRepository"
    private const val DEFAULT_PAGE = 1
    private var sInstance: MovieRepository? = null

    @JvmStatic
    fun getInstance(local: MovieLocalDataSource, remote: MovieRemoteDataSource): MovieRepository {
      if (sInstance == null) {
        synchronized(MovieRepository::javaClass) {
          sInstance = MovieRepository(local, remote)
        }
      }
      return sInstance!!
    }

    fun clearInstance() {
      sInstance = null
    }
  }
}
