package com.zackratos.ultimatebarx.library.extension

import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/26  4:45 PM
 * @email    : 869649338@qq.com
 * @Describe : Activity 的扩展方法和属性
 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.setSystemUiFlagWithLight(statusBarLight: Boolean, navigationBarLight: Boolean) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
    window?.decorView?.systemUiVisibility = systemUiFlag(statusBarLight, navigationBarLight)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.barTransparent() {
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
            if (window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS == 0)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (window.attributes.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION == 0)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
private fun systemUiFlag(statusBarLight: Boolean, navigationBarLight: Boolean): Int {

    var flag = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
            if (statusBarLight) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            if (navigationBarLight) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            if (statusBarLight) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    return flag
}

//@RequiresApi(Build.VERSION_CODES.KITKAT)
//internal fun FragmentActivity.commonNavigationBarExist(): Boolean {
//    val d = windowManager.defaultDisplay
//    val realDisplayMetrics = DisplayMetrics()
//
//    d.getRealMetrics(realDisplayMetrics)
//
//    val realHeight = realDisplayMetrics.heightPixels
//    val realWidth = realDisplayMetrics.widthPixels
//
//    val displayMetrics = DisplayMetrics()
//    d.getMetrics(displayMetrics)
//
//    val displayHeight = displayMetrics.heightPixels
//    val displayWidth = displayMetrics.widthPixels
//
//    return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
//}