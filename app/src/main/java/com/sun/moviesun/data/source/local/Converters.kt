package com.sun.moviesun.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sun.moviesun.data.model.Keyword
import com.sun.moviesun.data.model.Review
import com.sun.moviesun.data.model.Video

open class Converters {

  @TypeConverter
  fun fromStringToIntList(value: String): List<Int>? =
      Gson().fromJson<List<Int>>(value, object : TypeToken<List<Int>>() {}.type)

  @TypeConverter
  fun fromListIntToString(list: List<Int>?): String = Gson().toJson(list)

  @TypeConverter
  fun fromStringToKeywordList(value: String): List<Keyword>? =
      Gson().fromJson<List<Keyword>>(value, object : TypeToken<List<Keyword>>() {}.type)

  @TypeConverter
  fun fromListKeywordToString(list: List<Keyword>?): String = Gson().toJson(list)

  @TypeConverter
  fun fromStringToReviewList(value: String): List<Review>? =
      Gson().fromJson<List<Review>>(value, object : TypeToken<List<Review>>() {}.type)

  @TypeConverter
  fun fromListReviewToString(list: List<Review>?): String = Gson().toJson(list)

  @TypeConverter
  fun fromStringToVideoList(value: String): List<Video>? =
      Gson().fromJson<List<Video>>(value, object : TypeToken<List<Video>>() {}.type)

  @TypeConverter
  fun fromListVideoToString(list: List<Video>?): String = Gson().toJson(list)
}
