package com.sun.moviesun.data.annotation

import androidx.annotation.IntDef
import com.sun.moviesun.data.annotation.PageKeyDef.ACTOR
import com.sun.moviesun.data.annotation.PageKeyDef.DISCOVER
import com.sun.moviesun.data.annotation.PageKeyDef.FAVORITE
import com.sun.moviesun.data.annotation.PageKeyDef.GENRE
import com.sun.moviesun.data.annotation.PageKeyDef.SETTING

object PageKeyDef {
  const val DISCOVER = 0
  const val GENRE = 1
  const val FAVORITE = 2
  const val ACTOR = 3
  const val SETTING = 4
}

@Retention(AnnotationRetention.SOURCE)
@IntDef(DISCOVER, GENRE, FAVORITE, ACTOR, SETTING)
annotation class PageKey
