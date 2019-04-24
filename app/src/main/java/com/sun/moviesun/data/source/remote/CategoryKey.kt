package com.sun.moviesun.data.source.remote

import androidx.annotation.StringDef
import com.sun.moviesun.data.source.remote.CategoryKeyDef.NOW_PLAYING
import com.sun.moviesun.data.source.remote.CategoryKeyDef.POPULAR
import com.sun.moviesun.data.source.remote.CategoryKeyDef.TOP_RATED
import com.sun.moviesun.data.source.remote.CategoryKeyDef.UPCOMING

object CategoryKeyDef {
  const val TOP_RATED = "top_rated"
  const val NOW_PLAYING = "now_playing"
  const val POPULAR = "popular"
  const val UPCOMING = "upcoming"
}

@Retention(AnnotationRetention.SOURCE)
@StringDef(TOP_RATED, NOW_PLAYING, POPULAR, UPCOMING)
annotation class CategoryKey
