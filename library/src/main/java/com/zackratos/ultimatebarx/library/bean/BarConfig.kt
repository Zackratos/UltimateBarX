package com.zackratos.ultimatebarx.library.bean

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/26  7:27 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
data class BarConfig(
    /**
     * 是否忽略状态栏或导航栏的占位高度（相当于 [android.view.View.setFitsSystemWindows]）
     * true  : contentView 位于状态栏和导航栏之间（不占用状态栏和导航栏位置）
     * false : contentView 可以伸到状态栏和导航栏的位置（沉浸式）
     */
    internal var fitWindow: Boolean = false,
    @ColorInt
    internal var color: Int = 0,
    @DrawableRes
    internal var drawableRes: Int = 0,
    @ColorRes
    internal var colorRes: Int = 0,
    /**
     *  light 模式（状态栏字体颜色变灰，导航栏内部按钮颜色变灰）
     *  true  : 状态栏字体灰色，导航栏按钮灰色
     *  false : 状态栏字体白色，导航栏按钮白色
     */
    internal var light: Boolean = false
) {

    companion object {
        fun newInstance(): BarConfig =
            BarConfig().apply {
                color = Int.MIN_VALUE
                colorRes = -1
                drawableRes = -1
                fitWindow = true
            }
    }

    fun fitWindow(fitWindow: Boolean): BarConfig = apply { this.fitWindow = fitWindow }

    fun color(@ColorInt color: Int): BarConfig =
        apply {
            drawableRes = -1
            colorRes = -1
            this.color = color
        }

    fun colorRes(@ColorRes colorRes: Int): BarConfig =
        apply {
            drawableRes = -1
            color = Int.MIN_VALUE
            this.colorRes = colorRes
        }

    fun drawableRes(@DrawableRes drawableRes: Int): BarConfig =
        apply {
            color = Int.MIN_VALUE
            colorRes = -1
            this.drawableRes = drawableRes
        }

    fun light(light: Boolean): BarConfig = apply { this.light = light }

    fun transparent(): BarConfig =
        apply {
            fitWindow = false
            color = Color.TRANSPARENT
            colorRes = -1
            drawableRes = -1
        }


    internal fun update(config: BarConfig) {
        if (config == this) return
        this.fitWindow = config.fitWindow
        this.color = config.color
        this.drawableRes = config.drawableRes
        this.colorRes = config.colorRes
        this.light = config.light
    }

}