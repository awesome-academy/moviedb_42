package com.sun.moviesun.data.source.local

import com.sun.moviesun.data.source.MovieDataSource
import com.sun.moviesun.data.source.local.database.AppDatabase

class MovieLocalDataSource(
    private val database: AppDatabase
) : MovieDataSource.Local {

  companion object {

    private var sInstance: MovieLocalDataSource? = null

    @JvmStatic
    fun getInstance(database: AppDatabase): MovieLocalDataSource {
      if (sInstance == null) {
        synchronized(MovieLocalDataSource::javaClass) {
          sInstance = MovieLocalDataSource(database)
        }
      }
      return sInstance!!
    }

    fun clearInstance() { sInstance = null }
  }
}
