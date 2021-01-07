package com.zackratos.ultimatebarx.library

import android.content.Context
import android.os.Build
import android.view.View
import com.zackratos.ultimatebarx.library.core.addNavigationBarBottomPadding
import com.zackratos.ultimatebarx.library.core.addStatusBarTopPadding
import com.zackratos.ultimatebarx.library.extension.getNavigationBarHeight
import com.zackratos.ultimatebarx.library.extension.getStatusBarHeight

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/5  8:29 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
fun Context.getStatusBarHeight() = getStatusBarHeight()

fun Context.getNavigationBarHeight() = getNavigationBarHeight()

fun View.addStatusBarTopPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addStatusBarTopPadding()
}

fun View.addNavigationBarBottomPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addNavigationBarBottomPadding()
}