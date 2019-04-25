package com.sun.moviesun.data.source.local.dao

import androidx.room.*
import com.google.common.base.Optional
import com.sun.moviesun.data.model.entity.Movie
import io.reactivex.Flowable

@Dao
interface MovieDao {
  @Query("SELECT * FROM movies WHERE page = :page AND category = :category")
  fun getMovies(page: Int, category: String): Flowable<List<Movie>>

  @Query("SELECT * FROM movies WHERE id = :movieId AND category = :category")
  fun getMovie(movieId: Int, category: String): Flowable<Optional<Movie>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movie: Movie)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movies: List<Movie>)

  @Update
  fun updateMovie(movie: Movie)

  @Query("DELETE FROM movies WHERE id = :movieId")
  fun deleteMovieById(movieId: Int): Int

  @Query("DELETE FROM movies")
  fun deleteMovies()
}
