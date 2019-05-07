package com.sun.moviesun.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sun.moviesun.data.source.remote.api.Api
import com.sun.moviesun.util.extension.requestGlideListener

object BindingImage {

  @JvmStatic
  @BindingAdapter("bindImageUrl")
  fun bindImageUrl(imageView: ImageView, url: String?) {
    url?.let {
      Glide.with(imageView.context).load(Api.getPosterPath(it))
          .listener(imageView.context.requestGlideListener(imageView))
          .into(imageView)
    }
  }

  @JvmStatic
  @BindingAdapter("bindSlideUrl")
  fun bindSlideUrl(imageView: ImageView, url: String?) {
    url?.let {
      Glide.with(imageView.context).load(Api.getBackdropPath(it))
          .listener(imageView.context.requestGlideListener(imageView))
          .into(imageView)
    }
  }
}
