package com.zackratos.ultimatebarx.library.view

import com.zackratos.ultimatebarx.library.UltimateBarXManager

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 21:21
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal abstract class BaseCreator(protected val tag: Tag, protected val landscape: Boolean = false): Creator {

    protected val manager by lazy { UltimateBarXManager.getInstance() }

}