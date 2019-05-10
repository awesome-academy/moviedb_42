package com.sun.moviesun.data.repository

import com.sun.moviesun.data.model.MovieDetail
import com.sun.moviesun.data.source.MovieDataSource
import com.sun.moviesun.data.source.local.MovieLocalDataSource
import com.sun.moviesun.data.source.remote.MovieRemoteDataSource
import com.sun.moviesun.data.source.remote.response.*
import io.reactivex.Observable

class MovieRepository constructor(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) : MovieDataSource.Local, MovieDataSource.Remote {

  override fun getMoviesTrendingByDay(): Observable<MovieResponse> =
      remote.getMoviesTrendingByDay()

  override fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse> =
      remote.getMoviesCategory(category, page)

  override fun getKeywords(id: Int): Observable<KeywordResponse> =
      remote.getKeywords(id)

  override fun getVideos(id: Int): Observable<VideoResponse> =
      remote.getVideos(id)

  override fun getReviews(id: Int, page: Int): Observable<ReviewResponse> =
      remote.getReviews(id, page)

  override fun getMovie(id: Int): Observable<MovieDetail> =
      remote.getMovie(id)

  override fun getCredits(id: Int): Observable<CreditResponse> =
      remote.getCredits(id)

  override fun getGenres(): Observable<GenresResponse> =
      remote.getGenres()

  override fun getMoviesByGenre(genreId: Int, page: Int): Observable<MovieResponse> =
      remote.getMoviesByGenre(genreId, page)

  override fun searchMovie(keyword: String, page: Int): Observable<MovieResponse> =
      remote.searchMovie(keyword, page)

  companion object {
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

    fun clearInstance() { sInstance = null }
  }
}
