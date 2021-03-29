package com.zackratos.ultimatebarx.ultimatebarx.bean

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
    internal var background: BarBackground = BarBackground.newInstance(),
    /**
     *  light 模式（状态栏字体颜色变灰，导航栏内部按钮颜色变灰）
     *  true  : 状态栏字体灰色，导航栏按钮灰色
     *  false : 状态栏字体白色，导航栏按钮白色
     */
    internal var light: Boolean = false,
    /**
     *  低版本 light 模式下，状态栏或导航栏重新设置背景
     *  因为低版本不支持 Light 模式，为了防止状态栏文字和导航栏按钮看不到
     *  只在 light 模式下使用，非 light 模式使用无效
     */
    internal var lvLightBackground: BarBackground = BarBackground.newInstance()
) {

    companion object {
        fun newInstance(): BarConfig =
            BarConfig().apply {
                background.default()
                lvLightBackground.default()
                fitWindow = true
                light = false
            }
    }

    fun fitWindow(fitWindow: Boolean): BarConfig = apply { this.fitWindow = fitWindow }

    fun background(background: BarBackground): BarConfig =
        apply {
            this.background.update(background)
        }

    fun color(@ColorInt color: Int): BarConfig =
        apply {
            background.color(color)
        }

    fun colorRes(@ColorRes colorRes: Int): BarConfig =
        apply {
            background.colorRes(colorRes)
        }

    fun drawableRes(@DrawableRes drawableRes: Int): BarConfig =
        apply {
            background.drawableRes(drawableRes)
        }

    fun light(light: Boolean): BarConfig = apply { this.light = light }

    fun transparent(): BarConfig =
        apply {
            fitWindow = false
            background.transparent()
        }

    fun lvLightBackground(background: BarBackground): BarConfig =
        apply {
            lvLightBackground.update(background)
        }

    fun lvLightColor(@ColorInt color: Int): BarConfig =
        apply {
            lvLightBackground.color(color)
        }

    fun lvLightColorRes(@ColorRes colorRes: Int): BarConfig =
        apply {
            lvLightBackground.colorRes(colorRes)
        }

    fun lvLightDrawableRes(@DrawableRes drawableRes: Int): BarConfig =
        apply {
            lvLightBackground.drawableRes(drawableRes)
        }


    internal fun update(config: BarConfig) {
        if (config == this) return
        this.fitWindow = config.fitWindow
        this.background.update(config.background)
        this.lvLightBackground.update(config.lvLightBackground)
        this.light = config.light
    }

}