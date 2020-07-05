package com.zackratos.ultimatebarx.sample.extension

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/30  11:26 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
@ColorInt
fun Context.getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

private fun Context.getBarHeight(name: String): Int
        = resources.getDimensionPixelSize(resources.getIdentifier(name, "dimen", "android"))

internal fun Context.getStatusBarHeight() = getBarHeight("status_bar_height")

internal fun Context.getNavigationBarHeight() = getBarHeight("navigation_bar_height")