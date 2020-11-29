package com.zackratos.ultimatebarx.library.operator

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.zackratos.ultimatebarx.library.bean.BarConfig

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/27 1:41
 * @email    : 869649338@qq.com
 * @Describe :
 */
interface Operator {

    fun applyStatusBar()

    fun applyNavigationBar()

    fun config(config: BarConfig): Operator

    fun transparent(): Operator

    fun light(light: Boolean): Operator

    fun fitWindow(fitWindow: Boolean): Operator

    fun drawableRes(@DrawableRes drawableRes: Int): Operator

    fun colorRes(@ColorRes colorRes: Int): Operator

    fun colorInt(@ColorInt colorInt: Int): Operator

}