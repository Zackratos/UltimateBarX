package com.zackratos.ultimatebarx.library

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
    }

}