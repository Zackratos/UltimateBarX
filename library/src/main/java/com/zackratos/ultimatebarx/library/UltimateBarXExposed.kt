package com.zackratos.ultimatebarx.library

import android.os.Build
import android.view.View
import com.zackratos.ultimatebarx.library.core.addNavigationBarBottomPadding
import com.zackratos.ultimatebarx.library.core.addStatusBarTopPadding
import com.zackratos.ultimatebarx.library.extension.navigationBarHeight
import com.zackratos.ultimatebarx.library.extension.statusBarHeight

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/5  8:29 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */

val statusBarHeight: Int
    get() = UltimateBarXManager.getInstance().context.statusBarHeight

val navigationBarHeight: Int
    get() = UltimateBarXManager.getInstance().context.navigationBarHeight

fun View.addStatusBarTopPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addStatusBarTopPadding()
}

fun View.addNavigationBarBottomPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addNavigationBarBottomPadding()
}