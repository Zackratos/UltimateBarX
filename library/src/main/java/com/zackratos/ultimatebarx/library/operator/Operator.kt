package com.zackratos.ultimatebarx.library.operator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.UltimateBarXManager
import com.zackratos.ultimatebarx.library.bean.BarConfig

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/27 1:41
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal interface Operator {

    fun applyStatusBar(config: BarConfig)

    fun applyNavigationBar(config: BarConfig)

    companion object {
        fun get(activity: FragmentActivity): Operator {
            var operator = UltimateBarXManager.getInstance().getOperator(activity)
            if (operator == null) {
                operator = ActivityOperator(activity)
                UltimateBarXManager.getInstance().putOperator(activity, operator)
            }
            return operator
        }

        fun get(fragment: Fragment): Operator {
            var operator = UltimateBarXManager.getInstance().getOperator(fragment)
            if (operator == null) {
                operator = FragmentOperator(fragment)
                UltimateBarXManager.getInstance().putOperator(fragment, operator)
            }
            return operator
        }
    }
}