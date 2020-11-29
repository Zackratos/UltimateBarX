package com.zackratos.ultimatebarx.library.operator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/29 23:31
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal object OperatorProvider {

    internal fun get(activity: FragmentActivity): Operator = ActivityOperator.newInstance(activity)

    internal fun get(fragment: Fragment): Operator = FragmentOperator.newInstance(fragment)

}