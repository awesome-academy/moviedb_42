package com.sun.moviesun.ui.detail.movie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sun.moviesun.R
import com.sun.moviesun.data.model.*
import com.sun.moviesun.data.model.entity.Movie
import com.sun.moviesun.data.source.remote.api.Api
import com.sun.moviesun.databinding.MovieDetailActivityBinding
import com.sun.moviesun.util.extension.provideMovieRepository
import com.sun.moviesun.util.extension.simpleToolbarWithHome
import kotlinx.android.synthetic.main.layout_movie_detail_appbar.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailNavigator {

  private lateinit var movieDetailBinding: MovieDetailActivityBinding
  private lateinit var movieViewModel: MovieDetailViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    movieViewModel = MovieDetailViewModel(applicationContext.provideMovieRepository(), this)
    movieViewModel.setMovie(getMovieFromIntent())
    movieViewModel.loadData()
    movieDetailBinding = DataBindingUtil.setContentView(this, R.layout.movie_detail_activity)
    initializeUI()
  }

  private fun initializeUI() {
    simpleToolbarWithHome(toolbar, movieViewModel.getMovie().title!!)
    movieDetailBinding.run {
      viewModel = movieViewModel
    }
  }

  override fun onStop() {
    super.onStop()
    movieViewModel.onCleared()
  }

  private fun getMovieFromIntent(): Movie {
    return intent.getParcelableExtra(EXTRA_MOVIE) as Movie
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    item?.let {
      when (it.itemId) {
        android.R.id.home -> {
          onBackPressed()
        }
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onClickItemVideo(video: Video) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(video.key!!))))
  }

  override fun onClickItemReview(review: Review) {
  }

  override fun onClickItemCrew(crew: Crew) {
  }

  override fun onClickItemCast(cast: Cast) {
  }

  override fun onClickItemCompany(company: Company) {
  }

  companion object {

    private const val EXTRA_MOVIE = "com.sun.moviesun.EXTRA_MOVIE"

    fun newInstance(context: Context, movie: Movie): Intent =
        Intent(context, MovieDetailActivity::class.java).apply {
          putExtra(EXTRA_MOVIE, movie)
        }
  }
}
