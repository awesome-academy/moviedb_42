package com.sun.moviesun.data.source.remote.api

import com.sun.moviesun.data.annotation.CategoryKey
import com.sun.moviesun.data.source.remote.response.GenresResponse
import com.sun.moviesun.data.source.remote.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

  @GET("trending/movie/day")
  fun getMoviesTrendingByDay(): Observable<MovieResponse>

  @GET("movie/{type}?language=en&sort_by=popularity.desc")
  fun getMoviesCategory(@Path("type") @CategoryKey category: String?, @Query("page") page: Int): Observable<MovieResponse>

  @GET("genre/movie/list")
  fun getGenres(): Observable<GenresResponse>

  @GET("discover/movie?language=en&sort_by=popularity.desc")
  fun getMoviesByGenre(@Query("with_genres") genreId: Int, @Query("page") page: Int): Observable<MovieResponse>
}
