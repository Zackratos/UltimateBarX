package com.zackratos.ultimatebarx.ultimatebarx.operator

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/27 1:41
 * @email    : 869649338@qq.com
 * @Describe :
 */
@Deprecated("")
interface Operator {

    /**
     * please use
     * [com.zackratos.ultimatebarx.ultimatebarx.statusBar]
     * and
     * [com.zackratos.ultimatebarx.ultimatebarx.getStatusBar]
     */
    @Deprecated("")
    fun applyStatusBar()

    /**
     * please use
     * [com.zackratos.ultimatebarx.ultimatebarx.navigationBar]
     * and
     * [com.zackratos.ultimatebarx.ultimatebarx.getNavigationBar]
     */
    @Deprecated("")
    fun applyNavigationBar()

    /**
     * please use [BarConfig.update]
     */
    @Deprecated("")
    fun config(config: BarConfig): Operator

    /**
     * please use [BarConfig.transparent]
     */
    @Deprecated("")
    fun transparent(): Operator

    /**
     * please use [BarConfig.light]
     */
    @Deprecated("")
    fun light(light: Boolean): Operator

    /**
     * please use [BarConfig.fitWindow]
     */
    @Deprecated("")
    fun fitWindow(fitWindow: Boolean): Operator

    /**
     * please use [BarConfig.drawableRes]
     */
    @Deprecated("")
    fun drawableRes(@DrawableRes drawableRes: Int): Operator

    /**
     * please use [BarConfig.colorRes]
     */
    @Deprecated("")
    fun colorRes(@ColorRes colorRes: Int): Operator

    /**
     * please use [BarConfig.color]
     */
    @Deprecated("")
    fun color(@ColorInt color: Int): Operator

    /**
     * please use [BarConfig.background]
     */
    @Deprecated("")
    fun background(background: BarBackground): Operator

    /**
     * please use [BarConfig.lvlBackground]
     */
    @Deprecated("")
    fun lvLightBackground(background: BarBackground): Operator

    /**
     * please use [BarConfig.lvlDrawableRes]
     */
    @Deprecated("")
    fun lvLightDrawableRes(@DrawableRes drawableRes: Int): Operator

    /**
     * please use [BarConfig.lvlColorRes]
     */
    @Deprecated("")
    fun lvLightColorRes(@ColorRes colorRes: Int): Operator

    /**
     * please use [BarConfig.lvlColor]
     */
    @Deprecated("")
    fun lvLightColor(@ColorInt color: Int): Operator

}