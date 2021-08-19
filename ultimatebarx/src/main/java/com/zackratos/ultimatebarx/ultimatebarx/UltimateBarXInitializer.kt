package com.zackratos.ultimatebarx.ultimatebarx

import android.content.Context
import androidx.startup.Initializer

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/20  11:30 AM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class UltimateBarXInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        UltimateBarXManager.instance.context = context
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}