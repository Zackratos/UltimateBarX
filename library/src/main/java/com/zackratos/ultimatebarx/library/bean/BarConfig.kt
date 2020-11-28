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

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/26  7:27 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class BarConfig {

    /**
     * 是否忽略状态栏或导航栏的占位高度（相当于 [android.view.View.setFitsSystemWindows]）
     * true  : contentView 位于状态栏和导航栏之间（不占用状态栏和导航栏位置）
     * false : contentView 可以伸到状态栏和导航栏的位置（沉浸式）
     */
    internal var fitWindow: Boolean = false
    @ColorInt
    internal var bgColor: Int = 0
    @DrawableRes
    internal var bgRes: Int = 0
    @ColorRes
    internal var bgColorRes: Int = 0
    // light 模式（状态栏字体颜色变灰，导航栏内部按钮颜色变灰）
    internal var light: Boolean = false

    @Type
    private var type: Int = UltimateBarX.STATUS_BAR

    companion object {
        internal val DEFAULT_STATUS_BAR_CONFIG = Builder.newDefaultBuilder(UltimateBarX.STATUS_BAR).build()
        internal val DEFAULT_NAVIGATION_BAR_CONFIG = Builder.newDefaultBuilder(UltimateBarX.NAVIGATION_BAR).build()
    }

    private fun apply(activity: FragmentActivity) {
        when (type) {
            UltimateBarX.STATUS_BAR ->
                Operator.get(activity).applyStatusBar(this)

            UltimateBarX.NAVIGATION_BAR ->
                Operator.get(activity).applyNavigationBar(this)

        }
    }

    private fun apply(fragment: Fragment) {
        when (type) {
            UltimateBarX.STATUS_BAR ->
                Operator.get(fragment).applyStatusBar(this)

            UltimateBarX.NAVIGATION_BAR ->
                Operator.get(fragment).applyNavigationBar(this)
        }
    }


    internal fun update(config: BarConfig?) {
        if (config == null) return
        if (config == this) return
        this.fitWindow = config.fitWindow
        this.bgColor = config.bgColor
        this.bgRes = config.bgRes
        this.bgColorRes = config.bgColorRes
        this.light = config.light
    }

    class Builder(@Type private val type: Int) {
        private var fitWindow: Boolean = true
        @ColorInt
        private var bgColor: Int = Int.MIN_VALUE
        @DrawableRes
        private var bgRes: Int = -1
        @ColorRes
        private var bgColorRes: Int = -1
        private var light: Boolean = false
        private var transparent: Boolean = false

        companion object {
            fun newDefaultBuilder(@Type type: Int) =
                Builder(type).apply {
                    bgColor = Int.MIN_VALUE
                    bgColorRes = -1
                    bgRes = -1
                    fitWindow = true
                }
        }

        fun apply(activity: FragmentActivity) {
            build().apply(activity)
        }

        fun apply(fragment: Fragment) {
            build().apply(fragment)
        }

        fun bgColor(@ColorInt color: Int): Builder = apply {
            if (transparent) return@apply
            bgColor = color
        }

        fun bgColorRes(@ColorRes color: Int): Builder = apply {
            if (transparent) return@apply
            bgColorRes = color
        }

        fun fitWindow(fitWindow: Boolean): Builder = apply {
            if (transparent) return@apply
            this@Builder.fitWindow = fitWindow
        }

        fun bgRes(@DrawableRes bgRes: Int): Builder = apply {
            if (transparent) return@apply
            this@Builder.bgRes = bgRes
        }

        fun light(light: Boolean): Builder = apply { this@Builder.light = light }

        fun transparent(): Builder = apply {
            transparent = true
            fitWindow = false
            bgColor = Color.TRANSPARENT
            bgColorRes = -1
            bgRes = -1
        }

        internal fun build(): BarConfig =
            BarConfig().apply {
                type = this@Builder.type
                fitWindow = this@Builder.fitWindow
                bgColor = this@Builder.bgColor
                bgColorRes = this@Builder.bgColorRes
                bgRes = this@Builder.bgRes
                light = this@Builder.light
            }
    }
}