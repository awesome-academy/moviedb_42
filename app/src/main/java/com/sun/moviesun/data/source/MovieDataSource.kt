package com.sun.moviesun.data.source

import com.sun.moviesun.data.source.remote.response.MovieResponse
import io.reactivex.Observable

interface MovieDataSource {
  interface Local {}

  interface Remote {
    fun getMoviesTrendingByDay(): Observable<MovieResponse>
    fun getMoviesCategory(category: String?, page: Int): Observable<MovieResponse>
  }
}
