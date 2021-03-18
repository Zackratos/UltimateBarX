package com.zackratos.ultimatebarx.ultimatebarx.operator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarXManager

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/29 23:31
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal object OperatorProvider {

    internal fun create(activity: FragmentActivity): Operator = ActivityOperator.newInstance(activity)

    internal fun create(fragment: Fragment): Operator = FragmentOperator.newInstance(fragment)

    internal fun get(activity: FragmentActivity): Operator {
        val staConfig = UltimateBarXManager.getInstance().getStatusBarConfig(activity)
        val navConfig = UltimateBarXManager.getInstance().getNavigationBarConfig(activity)
        return DoubleOperator.newInstance()
            .staOperator(ActivityOperator.newInstance(activity, staConfig))
            .navOperator(ActivityOperator.newInstance(activity, navConfig))
    }

    internal fun get(fragment: Fragment): Operator {
        val staConfig = UltimateBarXManager.getInstance().getStatusBarConfig(fragment)
        val navConfig = UltimateBarXManager.getInstance().getNavigationBarConfig(fragment)
        return DoubleOperator.newInstance()
            .staOperator(FragmentOperator.newInstance(fragment, staConfig))
            .navOperator(FragmentOperator.newInstance(fragment, navConfig))
    }

}