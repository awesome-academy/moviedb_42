package com.sun.moviesun.util.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.sun.moviesun.ui.home.discover.SliderAdapter
import java.util.*

object BindingUtils {

  private const val FIRST_INDEX = 0
  private const val DELAY = 5000L
  private const val PLUS = 1
  private const val DURATION = 5000L

  @JvmStatic
  @BindingAdapter("bindAutoSwitchSlide")
  fun bindAutoSwitchSlide(pager: ViewPager, isAuto: Boolean = false) {
    val timerTask = object : TimerTask() {
      override fun run() {
        pager.post(Runnable {
          if (pager.currentItem > pager.childCount + PLUS) {
            pager.currentItem = FIRST_INDEX
            SliderAdapter.setCurrentPosition(pager.currentItem)
            return@Runnable
          }
          pager.currentItem = pager.currentItem + PLUS
          SliderAdapter.setCurrentPosition(pager.currentItem)
        })
      }
    }
    if (isAuto) {
      val timer = Timer()
      timer.schedule(timerTask, DELAY, DURATION)
    }
  }
}
