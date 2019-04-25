package com.sun.moviesun.data.source.remote

import com.sun.moviesun.data.model.network.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

  @GET("trending/movie/day")
  fun getMoviesTrendingByDay(): Flowable<ApiResponse<MovieResponse>>

  @GET("movie/{type}?language=en&sort_by=popularity.desc")
  fun getMoviesCategory(@Path("type") @CategoryKey category: String?, @Query("page") page: Int): Flowable<ApiResponse<MovieResponse>>
}
