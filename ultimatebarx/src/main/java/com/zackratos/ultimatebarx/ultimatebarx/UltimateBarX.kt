package com.zackratos.ultimatebarx.ultimatebarx

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import com.zackratos.ultimatebarx.ultimatebarx.operator.Operator
import com.zackratos.ultimatebarx.ultimatebarx.operator.OperatorProvider

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
        fun getStatusBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.instance.getStatusBarConfig(activity)

        @JvmStatic
        fun getNavigationBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.instance.getNavigationBarConfig(activity)

        @JvmStatic
        fun getStatusBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.instance.getStatusBarConfig(fragment)

        @JvmStatic
        fun getNavigationBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.instance.getNavigationBarConfig(fragment)

        @JvmStatic
        fun getStatusBarHeight() = statusBarHeight

        @JvmStatic
        fun getNavigationBarHeight() = navigationBarHeight

        @JvmStatic
        fun addStatusBarTopPadding(target: View) = target.addStatusBarTopPadding()

        @JvmStatic
        fun addNavigationBarBottomPadding(target: View) = target.addNavigationBarBottomPadding()
    }

}