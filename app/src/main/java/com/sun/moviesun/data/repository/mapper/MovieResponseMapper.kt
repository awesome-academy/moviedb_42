package com.sun.moviesun.data.repository.mapper

import com.sun.moviesun.data.model.network.MovieResponse

class MovieResponseMapper : NetworkResponseMapper<MovieResponse> {
  override fun onLastPage(response: MovieResponse): Boolean {
    return response.page > response.totalPages
  }
}
