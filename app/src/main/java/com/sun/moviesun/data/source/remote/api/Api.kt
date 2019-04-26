package com.sun.moviesun.data.source.remote.api

object Api {
  private const val API_KEY = "api_key"
  private const val URL = "https://api.themoviedb.org/3/"
  private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
  private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"

  fun getApiKey() = API_KEY

  fun getUrl() = URL

  fun getPosterPath(posterPath: String) = BASE_POSTER_PATH + posterPath

  fun getBackdropPath(backdropPath: String) = BASE_BACKDROP_PATH + backdropPath
}
