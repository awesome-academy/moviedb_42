package com.sun.moviesun.data.source.remote.api

import com.sun.moviesun.data.annotation.CategoryKey
import com.sun.moviesun.data.model.MovieDetail
import com.sun.moviesun.data.source.remote.response.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

  @GET("trending/movie/day")
  fun getMoviesTrendingByDay(): Observable<MovieResponse>

  @GET("movie/{type}?language=en&sort_by=popularity.desc")
  fun getMoviesCategory(@Path("type") @CategoryKey category: String?, @Query("page") page: Int): Observable<MovieResponse>

  @GET("movie/{movie_id}/keywords")
  fun getKeywords(@Path("movie_id") id: Int): Observable<KeywordResponse>

  @GET("movie/{movie_id}/videos")
  fun getVideos(@Path("movie_id") id: Int): Observable<VideoResponse>

  @GET("movie/{movie_id}/reviews")
  fun getReviews(@Path("movie_id") id: Int, @Query("page") page: Int): Observable<ReviewResponse>

  @GET("movie/{movie_id}")
  fun getMovie(@Path("movie_id") id: Int): Observable<MovieDetail>

  @GET("movie/{movie_id}/credits")
  fun getCredits(@Path("movie_id") id: Int): Observable<CreditResponse>

  @GET("genre/movie/list")
  fun getGenres(): Observable<GenresResponse>

  @GET("discover/movie?language=en&sort_by=popularity.desc")
  fun getMoviesByGenre(@Query("with_genres") genreId: Int, @Query("page") page: Int): Observable<MovieResponse>

  @GET("search/{type}?language=en&sort_by=popularity.desc")
  fun searchDataByType(@Path("type") type: String, @Query("query") keyword: String, @Query("page") page: Int): Observable<MovieResponse>
}
