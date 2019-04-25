package com.sun.moviesun.data.repository.mapper

import com.sun.moviesun.data.model.network.NetworkResponse

interface NetworkResponseMapper<in FROM : NetworkResponse> {
  fun onLastPage(response: FROM): Boolean
}
