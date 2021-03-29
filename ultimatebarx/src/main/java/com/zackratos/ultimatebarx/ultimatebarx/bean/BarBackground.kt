package com.zackratos.ultimatebarx.ultimatebarx.bean

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/3/24  4:08 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
data class BarBackground(
    @ColorInt
    internal var color: Int = 0,
    @DrawableRes
    internal var drawableRes: Int = 0,
    @ColorRes
    internal var colorRes: Int = 0) {

    companion object {
        fun newInstance(): BarBackground = BarBackground()
    }

    fun default() {
        color = Int.MIN_VALUE
        colorRes = -1
        drawableRes = -1
    }

    fun color(@ColorInt color: Int): BarBackground =
        apply {
            drawableRes = -1
            colorRes = -1
            this.color = color
        }

    fun colorRes(@ColorRes colorRes: Int): BarBackground =
        apply {
            drawableRes = -1
            color = Int.MIN_VALUE
            this.colorRes = colorRes
        }

    fun drawableRes(@DrawableRes drawableRes: Int): BarBackground =
        apply {
            color = Int.MIN_VALUE
            colorRes = -1
            this.drawableRes = drawableRes
        }

    fun transparent(): BarBackground =
        apply {
            color = Color.TRANSPARENT
            colorRes = -1
            drawableRes = -1
        }

    fun update(background: BarBackground) {
        if (background == this) return
        this.color = background.color
        this.drawableRes = background.drawableRes
        this.colorRes = background.colorRes
    }

}