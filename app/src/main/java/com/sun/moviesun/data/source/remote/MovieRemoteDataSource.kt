package com.sun.moviesun.data.source.remote

import com.sun.moviesun.data.annotation.SearchKeyDef
import com.sun.moviesun.data.model.MovieDetail
import com.sun.moviesun.data.source.MovieDataSource
import com.sun.moviesun.data.source.remote.api.MovieService
import com.sun.moviesun.data.source.remote.connect.RetrofitClient
import com.sun.moviesun.data.source.remote.response.*
import io.reactivex.Observable

class MovieRemoteDataSource private constructor(
    retrofitClient: RetrofitClient
): MovieDataSource.Remote {

  private var requestService : MovieService = retrofitClient.getMovieService()!!

  override fun getMoviesTrendingByDay(): Observable<MovieResponse> =
      requestService.getMoviesTrendingByDay()

  override fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse> =
      requestService.getMoviesCategory(category, page)

  override fun getKeywords(id: Int): Observable<KeywordResponse> =
      requestService.getKeywords(id)

  override fun getVideos(id: Int): Observable<VideoResponse> =
      requestService.getVideos(id)

  override fun getReviews(id: Int, page: Int): Observable<ReviewResponse> =
      requestService.getReviews(id, page)

  override fun getMovie(id: Int): Observable<MovieDetail> =
      requestService.getMovie(id)

  override fun getCredits(id: Int): Observable<CreditResponse> =
      requestService.getCredits(id)

  override fun getGenres(): Observable<GenresResponse> =
      requestService.getGenres()

  override fun getMoviesByGenre(genreId: Int, page: Int): Observable<MovieResponse> =
      requestService.getMoviesByGenre(genreId, page)

  override fun searchMovie(keyword: String, page: Int): Observable<MovieResponse> =
      requestService.searchDataByType(SearchKeyDef.MOVIE, keyword, page)

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
