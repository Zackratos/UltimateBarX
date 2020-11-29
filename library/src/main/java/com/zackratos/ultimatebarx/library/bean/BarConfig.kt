package com.zackratos.ultimatebarx.library.bean

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.library.annotation.Type
import com.zackratos.ultimatebarx.library.operator.Operator
import com.zackratos.ultimatebarx.library.operator.OperatorProvider

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/26  7:27 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class BarConfig private constructor() {

    /**
     * 是否忽略状态栏或导航栏的占位高度（相当于 [android.view.View.setFitsSystemWindows]）
     * true  : contentView 位于状态栏和导航栏之间（不占用状态栏和导航栏位置）
     * false : contentView 可以伸到状态栏和导航栏的位置（沉浸式）
     */
    internal var fitWindow: Boolean = false
    @ColorInt
    internal var colorInt: Int = 0
    @DrawableRes
    internal var drawableRes: Int = 0
    @ColorRes
    internal var colorRes: Int = 0

    /**
     *  light 模式（状态栏字体颜色变灰，导航栏内部按钮颜色变灰）
     *  true  : 状态栏字体灰色，导航栏按钮灰色
     *  false : 状态栏字体白色，导航栏按钮白色
     */
    internal var light: Boolean = false

    @Type
    private var type: Int = UltimateBarX.STATUS_BAR

    companion object {
        internal val DEFAULT_STATUS_BAR_CONFIG = Builder.newDefaultBuilder(UltimateBarX.STATUS_BAR).build()
        internal val DEFAULT_NAVIGATION_BAR_CONFIG = Builder.newDefaultBuilder(UltimateBarX.NAVIGATION_BAR).build()

        internal fun newInstance(): BarConfig =
            BarConfig().apply {
                colorInt = Int.MIN_VALUE
                colorRes = -1
                drawableRes = -1
                fitWindow = true
            }
    }

    fun fitWindow(fitWindow: Boolean): BarConfig = apply { this.fitWindow = fitWindow }

    fun colorInt(@ColorInt colorInt: Int): BarConfig = apply { this.colorInt = colorInt }

    fun colorRes(@ColorRes colorRes: Int): BarConfig = apply { this.colorRes = colorRes }

    fun drawableRes(@DrawableRes drawableRes: Int): BarConfig = apply { this.drawableRes = drawableRes }

    fun light(light: Boolean): BarConfig = apply { this.light = light }

    @Deprecated("")
    private fun apply(activity: FragmentActivity) {
        when (type) {
            UltimateBarX.STATUS_BAR ->
                OperatorProvider.get(activity).config(this).applyStatusBar()

            UltimateBarX.NAVIGATION_BAR ->
                OperatorProvider.get(activity).config(this).applyNavigationBar()

        }
    }

    @Deprecated("")
    private fun apply(fragment: Fragment) {
        when (type) {
            UltimateBarX.STATUS_BAR ->
                OperatorProvider.get(fragment).config(this).applyStatusBar()

            UltimateBarX.NAVIGATION_BAR ->
                OperatorProvider.get(fragment).config(this).applyNavigationBar()
        }
    }


    internal fun update(config: BarConfig?) {
        if (config == null) return
        if (config == this) return
        this.fitWindow = config.fitWindow
        this.colorInt = config.colorInt
        this.drawableRes = config.drawableRes
        this.colorRes = config.colorRes
        this.light = config.light
    }

    class Builder(@Type private val type: Int) {
        private var fitWindow: Boolean = true
        @ColorInt
        private var colorInt: Int = Int.MIN_VALUE
        @DrawableRes
        private var drawableRes: Int = -1
        @ColorRes
        private var colorRes: Int = -1
        private var light: Boolean = false
        private var transparent: Boolean = false

        companion object {
            fun newDefaultBuilder(@Type type: Int) =
                Builder(type).apply {
                    colorInt = Int.MIN_VALUE
                    colorRes = -1
                    drawableRes = -1
                    fitWindow = true
                }
        }

        /**
         *  use [com.zackratos.ultimatebarx.library.UltimateBarX.with]
         *  and [com.zackratos.ultimatebarx.library.operator.Operator.applyStatusBar]
         *  and [com.zackratos.ultimatebarx.library.operator.Operator.applyNavigationBar]
         */
        @Deprecated("")
        fun apply(activity: FragmentActivity) {
            build().apply(activity)
        }

        /**
         *  use [com.zackratos.ultimatebarx.library.UltimateBarX.with]
         *  and [com.zackratos.ultimatebarx.library.operator.Operator.applyStatusBar]
         *  and [com.zackratos.ultimatebarx.library.operator.Operator.applyNavigationBar]
         */
        @Deprecated("")
        fun apply(fragment: Fragment) {
            build().apply(fragment)
        }

        @Deprecated("", ReplaceWith("colorInt(colorInt)"))
        fun bgColor(@ColorInt colorInt: Int): Builder = colorInt(colorInt)

        fun colorInt(@ColorInt colorInt: Int): Builder =
            apply {
                if (transparent) return@apply
                this.colorInt = colorInt
            }

        @Deprecated("", ReplaceWith("colorRes(colorRes)"))
        fun bgColorRes(@ColorRes colorRes: Int): Builder = colorRes(colorRes)

        fun colorRes(@ColorRes colorRes: Int): Builder =
            apply {
                if (transparent) return@apply
                this.colorRes = colorRes
            }

        fun fitWindow(fitWindow: Boolean): Builder = apply {
            if (transparent) return@apply
            this@Builder.fitWindow = fitWindow
        }

        @Deprecated("", ReplaceWith("drawableRes(drawableRes)"))
        fun bgRes(@DrawableRes drawableRes: Int): Builder = drawableRes(drawableRes)

        fun drawableRes(@DrawableRes drawableRes: Int): Builder =
            apply {
                if (transparent) return@apply
                this@Builder.drawableRes = drawableRes
            }

        fun light(light: Boolean): Builder = apply { this@Builder.light = light }

        fun transparent(): Builder = apply {
            transparent = true
            fitWindow = false
            colorInt = Color.TRANSPARENT
            colorRes = -1
            drawableRes = -1
        }

        internal fun build(): BarConfig =
            BarConfig().apply {
                type = this@Builder.type
                fitWindow = this@Builder.fitWindow
                colorInt = this@Builder.colorInt
                colorRes = this@Builder.colorRes
                drawableRes = this@Builder.drawableRes
                light = this@Builder.light
            }
    }
}