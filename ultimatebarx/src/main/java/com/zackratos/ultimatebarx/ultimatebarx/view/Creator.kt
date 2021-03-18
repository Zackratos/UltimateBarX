package com.zackratos.ultimatebarx.ultimatebarx.view

import android.content.Context
import android.view.View

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 19:35
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal interface Creator {

    fun getStatusBarView(context: Context, fitWindow: Boolean): View

    fun getNavigationBarView(context: Context, fitWindow: Boolean): View

}