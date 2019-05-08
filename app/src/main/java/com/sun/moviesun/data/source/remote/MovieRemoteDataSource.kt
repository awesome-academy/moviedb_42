package com.sun.moviesun.data.source.remote

import com.sun.moviesun.data.source.MovieDataSource
import com.sun.moviesun.data.source.remote.api.MovieService
import com.sun.moviesun.data.source.remote.connect.RetrofitClient
import com.sun.moviesun.data.source.remote.response.GenresResponse
import com.sun.moviesun.data.source.remote.response.MovieResponse
import io.reactivex.Observable

class MovieRemoteDataSource private constructor(
    retrofitClient: RetrofitClient
): MovieDataSource.Remote {

  private var requestService : MovieService = retrofitClient.getMovieService()!!

  override fun getMoviesTrendingByDay(): Observable<MovieResponse> =
      requestService.getMoviesTrendingByDay()

  override fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse> =
      requestService.getMoviesCategory(category, page)

  override fun getGenres(): Observable<GenresResponse> =
      requestService.getGenres()

  override fun getMoviesByGenre(genreId: Int, page: Int): Observable<MovieResponse> =
      requestService.getMoviesByGenre(genreId, page)


  companion object {
    private var sInstance: MovieRemoteDataSource? = null

    @JvmStatic
    fun getInstance(retrofitClient: RetrofitClient): MovieRemoteDataSource {
      if (sInstance == null) {
        synchronized(MovieRemoteDataSource::javaClass) {
          sInstance = MovieRemoteDataSource(retrofitClient)
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
