package com.zackratos.ultimatebarx.library.operator

import com.zackratos.ultimatebarx.library.UltimateBarXManager

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 17:29
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal abstract class BaseOperator: Operator {


    protected val manager: UltimateBarXManager by lazy { UltimateBarXManager.getInstance() }

}