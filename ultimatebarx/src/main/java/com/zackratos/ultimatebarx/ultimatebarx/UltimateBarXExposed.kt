package com.zackratos.ultimatebarx.ultimatebarx

import android.os.Build
import android.view.View
import com.zackratos.ultimatebarx.ultimatebarx.core.addNavigationBarBottomPadding
import com.zackratos.ultimatebarx.ultimatebarx.core.addStatusBarTopPadding
import com.zackratos.ultimatebarx.ultimatebarx.extension.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.extension.statusBarHeight

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/5  8:29 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */

val statusBarHeight: Int
    get() = UltimateBarXManager.instance.context.statusBarHeight

val navigationBarHeight: Int
    get() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
            return 0
        val rom = UltimateBarXManager.instance.rom
        val context = UltimateBarXManager.instance.context
        if (!rom.navigationBarExist(context)) return 0
        return UltimateBarXManager.instance.context.navigationBarHeight
    }

fun View.addStatusBarTopPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addStatusBarTopPadding()
}

fun View.addNavigationBarBottomPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addNavigationBarBottomPadding()
}