package com.zackratos.ultimatebarx.library.extension

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/28  7:09 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */

private fun Context.getBarHeight(name: String): Int
        = resources.getDimensionPixelSize(resources.getIdentifier(name, "dimen", "android"))

internal val Context.statusBarHeight: Int
    get() = getBarHeight("status_bar_height")

internal val Context.navigationBarHeight: Int
    get() = getBarHeight("navigation_bar_height")

@ColorInt
internal fun Context.getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

internal val Context.screenHeight: Int
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    get() {
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        wm.defaultDisplay.getRealSize(point)
        return point.y
    }