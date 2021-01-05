package com.zackratos.ultimatebarx.library

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.operator.Operator
import com.zackratos.ultimatebarx.library.operator.OperatorProvider

class UltimateBarX {
    companion object {

        @JvmStatic
        fun with(activity: FragmentActivity): Operator = OperatorProvider.create(activity)

        @JvmStatic
        fun with(fragment: Fragment): Operator = OperatorProvider.create(fragment)

        @JvmStatic
        fun get(activity: FragmentActivity): Operator = OperatorProvider.get(activity)

        @JvmStatic
        fun get(fragment: Fragment): Operator = OperatorProvider.get(fragment)

        @JvmStatic
        fun getStatusBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.getInstance().getStatusBarConfig(activity)

        @JvmStatic
        fun getNavigationBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.getInstance().getNavigationBarConfig(activity)

        @JvmStatic
        fun getStatusBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.getInstance().getStatusBarConfig(fragment)

        @JvmStatic
        fun getNavigationBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.getInstance().getNavigationBarConfig(fragment)

        /**
         *  给 View 的外层嵌套一个 FrameLayout，使被嵌套的 View 的顶部有个状态栏高度的 margin
         *  一般在状态栏透明且可被侵入的时候使用
         */
        @JvmStatic
        fun addStatusBarTopMarginWrapper(target: View): FrameLayout? = target.addStatusBarTopMarginWrapper()

        /**
         *  给 View 的外层嵌套一个 FrameLayout，使被嵌套的 View 的底部有个导航栏高度的 margin
         *  一般在导航栏透明且可被侵入的时候使用
         */
        @JvmStatic
        fun addNavigationBarBottomMarginWrapper(target: View): FrameLayout? = target.addNavigationBarBottomMarginWrapper()

        @JvmStatic
        fun getStatusBarHeight(context: Context) = context.getStatusBarHeight()

        @JvmStatic
        fun getNavigationBarHeight(context: Context) = context.getNavigationBarHeight()
    }

}