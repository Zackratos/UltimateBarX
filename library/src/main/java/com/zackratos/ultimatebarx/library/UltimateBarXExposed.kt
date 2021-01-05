package com.zackratos.ultimatebarx.library

import android.os.Build
import android.view.View
import android.widget.FrameLayout
import com.zackratos.ultimatebarx.library.core.addStatusBarTopMarginWrapper
import com.zackratos.ultimatebarx.library.core.addNavigationBarBottomMarginWrapper

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/5  8:29 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
/**
 *  给 View 的外层嵌套一个 FrameLayout，使被嵌套的 View 的顶部有个状态栏高度的 margin
 *  一般在状态栏透明且可被侵入的时候使用
 */
fun View.addStatusBarTopMarginWrapper(): FrameLayout? {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        return null
    }
    return addStatusBarTopMarginWrapper()
}

/**
 *  给 View 的外层嵌套一个 FrameLayout，使被嵌套的 View 的底部有个导航栏高度的 margin
 *  一般在导航栏透明且可被侵入的时候使用
 */
fun View.addNavigationBarBottomMarginWrapper(): FrameLayout? {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        return null
    }
    return addNavigationBarBottomMarginWrapper()
}