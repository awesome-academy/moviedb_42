package com.sun.moviesun.data.source

import com.sun.moviesun.data.model.MovieDetail
import com.sun.moviesun.data.source.remote.response.*
import io.reactivex.Observable

interface MovieDataSource {
  interface Local {}

  interface Remote {
    fun getMoviesTrendingByDay(): Observable<MovieResponse>
    fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse>
    fun getKeywords(id: Int): Observable<KeywordResponse>
    fun getVideos(id: Int): Observable<VideoResponse>
    fun getReviews(id: Int, page: Int): Observable<ReviewResponse>
    fun getMovie(id: Int): Observable<MovieDetail>
    fun getCredits(id: Int): Observable<CreditResponse>
    fun getGenres(): Observable<GenresResponse>
    fun getMoviesByGenre(genreId: Int, page: Int): Observable<MovieResponse>
    fun searchMovie(keyword: String, page: Int): Observable<MovieResponse>
  }
}
