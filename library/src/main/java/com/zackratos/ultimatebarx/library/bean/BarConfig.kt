package com.zackratos.ultimatebarx.library.bean

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.library.annotation.Type
import com.zackratos.ultimatebarx.library.operator.OperatorProvider

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

    @Type
    private var type: Int = UltimateBarX.STATUS_BAR

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

    @Deprecated("")
    private fun apply(activity: FragmentActivity) {
        when (type) {
            UltimateBarX.STATUS_BAR ->
                OperatorProvider.create(activity).config(this).applyStatusBar()

            UltimateBarX.NAVIGATION_BAR ->
                OperatorProvider.create(activity).config(this).applyNavigationBar()

        }
    }

    @Deprecated("")
    private fun apply(fragment: Fragment) {
        when (type) {
            UltimateBarX.STATUS_BAR ->
                OperatorProvider.create(fragment).config(this).applyStatusBar()

            UltimateBarX.NAVIGATION_BAR ->
                OperatorProvider.create(fragment).config(this).applyNavigationBar()
        }
    }


    internal fun update(config: BarConfig) {
        if (config == this) return
        this.fitWindow = config.fitWindow
        this.color = config.color
        this.drawableRes = config.drawableRes
        this.colorRes = config.colorRes
        this.light = config.light
    }

    @Deprecated("")
    class Builder(@Type private val type: Int) {
        private var fitWindow: Boolean = true
        @ColorInt
        private var color: Int = Int.MIN_VALUE
        @DrawableRes
        private var drawableRes: Int = -1
        @ColorRes
        private var colorRes: Int = -1
        private var light: Boolean = false
        @Deprecated("")
        private var transparent: Boolean = false

        companion object {
            @Deprecated("")
            fun newDefaultBuilder(@Type type: Int) =
                Builder(type).apply {
                    color = Int.MIN_VALUE
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
        fun bgColor(@ColorInt colorInt: Int): Builder = color(colorInt)

        @Deprecated("")
        fun color(@ColorInt colorInt: Int): Builder =
            apply {
                if (transparent) return@apply
                this.color = colorInt
            }

        @Deprecated("", ReplaceWith("colorRes(colorRes)"))
        fun bgColorRes(@ColorRes colorRes: Int): Builder = colorRes(colorRes)

        @Deprecated("")
        fun colorRes(@ColorRes colorRes: Int): Builder =
            apply {
                if (transparent) return@apply
                this.colorRes = colorRes
            }

        @Deprecated("")
        fun fitWindow(fitWindow: Boolean): Builder = apply {
            if (transparent) return@apply
            this@Builder.fitWindow = fitWindow
        }

        @Deprecated("", ReplaceWith("drawableRes(drawableRes)"))
        fun bgRes(@DrawableRes drawableRes: Int): Builder = drawableRes(drawableRes)

        @Deprecated("")
        fun drawableRes(@DrawableRes drawableRes: Int): Builder =
            apply {
                if (transparent) return@apply
                this@Builder.drawableRes = drawableRes
            }

        @Deprecated("")
        fun light(light: Boolean): Builder = apply { this@Builder.light = light }

        @Deprecated("")
        fun transparent(): Builder = apply {
            transparent = true
            fitWindow = false
            color = Color.TRANSPARENT
            colorRes = -1
            drawableRes = -1
        }

        @Deprecated("")
        internal fun build(): BarConfig =
            BarConfig().apply {
                type = this@Builder.type
                fitWindow = this@Builder.fitWindow
                color = this@Builder.color
                colorRes = this@Builder.colorRes
                drawableRes = this@Builder.drawableRes
                light = this@Builder.light
            }
    }
}