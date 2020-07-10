package com.zackratos.ultimatebarx.library.extension

import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.R
import com.zackratos.ultimatebarx.library.bean.BarConfig

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/26  4:45 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe : Activity 的扩展方法和属性
 */
private const val TAG_PARENT = "activity_root_view_parent"
private const val TAG_STATUS_BAR = "activity_status_bar"
private const val TAG_NAVIGATION_BAR = "activity_navigation_bar"


@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.transparentStatusAndNavigationBar(statusBarLight: Boolean, navigationBarLight: Boolean) {
    val decorView = window?.decorView
    var parentView: ViewGroup? = decorView?.findViewWithTag(TAG_PARENT)
    if (parentView == null) {
        parentView = findViewById(android.R.id.content)
        parentView?.tag = TAG_PARENT
        parentView?.clipToPadding = false
    }
    parentView?.getChildAt(0)?.fitsSystemWindows = false
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
            decorView?.systemUiVisibility = systemUiFlag(statusBarLight, navigationBarLight)
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

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.updateStatusBarView(config: BarConfig?): View? {
    if (config == null) return null
    val statusBar = initStatusBarView(config.fitWindow)
    when {
        config.bgRes > 0 -> statusBar.setBackgroundResource(config.bgRes)
        config.bgColor > Int.MIN_VALUE -> statusBar.setBackgroundColor(config.bgColor)
        config.bgColorRes > 0 -> statusBar.setBackgroundColor(ContextCompat.getColor(this, config.bgColorRes))
        else -> statusBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
    }
    return statusBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.updateNavigationBarView(config: BarConfig?): View? {
    if (config == null) return null
    val navigationBar = initNavigationBarView(config.fitWindow) ?: return null
    when {
        config.bgRes > 0 -> navigationBar.setBackgroundResource(config.bgRes)
        config.bgColor > Int.MIN_VALUE -> navigationBar.setBackgroundColor(config.bgColor)
        config.bgColorRes > 0 -> navigationBar.setBackgroundColor(ContextCompat.getColor(this, config.bgColorRes))
        else -> navigationBar.setBackgroundColor(Color.BLACK)
    }
    return navigationBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun FragmentActivity.initStatusBarView(fitWindow: Boolean): View {
    val decorView = window.decorView as FrameLayout?
    val parentView: ViewGroup? = decorView?.findViewWithTag(TAG_PARENT)
    parentView?.post {
        parentView.setPadding(0, if (fitWindow) getStatusBarHeight() else 0, 0, parentView.paddingBottom)
    }
    var statusBar: View? = parentView?.findViewWithTag(TAG_STATUS_BAR)
    if (statusBar == null) {
        statusBar = createStatusBarView()
        statusBar.tag = TAG_STATUS_BAR
        parentView?.addView(statusBar)
    }
    statusBar.layoutParams = (statusBar.layoutParams as FrameLayout.LayoutParams)
        .apply { topMargin = if (fitWindow) -getStatusBarHeight() else 0 }
    return statusBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun FragmentActivity.initNavigationBarView(fitWindow: Boolean): View? {
    if (!navigationBarExist()) return null
    val decorView = window.decorView as FrameLayout?
    val parentView: ViewGroup? = decorView?.findViewWithTag(TAG_PARENT)
    parentView?.post {
        parentView.setPadding(0,  parentView.paddingTop, 0, if (fitWindow) getNavigationBarHeight() else 0)
    }
    var navigationBar: View? = parentView?.findViewWithTag(TAG_NAVIGATION_BAR)
    if (navigationBar == null) {
        navigationBar = createNavigationBarView()
        navigationBar.tag = TAG_NAVIGATION_BAR
        parentView?.addView(navigationBar)
    }
    navigationBar.layoutParams = (navigationBar.layoutParams as FrameLayout.LayoutParams)
        .apply { bottomMargin = if (fitWindow) -getNavigationBarHeight() else 0 }
    return navigationBar
}

internal fun FragmentActivity.createStatusBarView(): View =
    View(this)
        .apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight()
            ).apply { gravity = Gravity.TOP }
        }

internal fun FragmentActivity.createNavigationBarView(): View =
    View(this)
        .apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getNavigationBarHeight()
            ).apply { gravity = Gravity.BOTTOM }
        }

// 导航栏是否存在
internal fun FragmentActivity.navigationBarExist(): Boolean {

    val d = windowManager.defaultDisplay
    val realDisplayMetrics = DisplayMetrics()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        d.getRealMetrics(realDisplayMetrics)
    }

    val realHeight = realDisplayMetrics.heightPixels
    val realWidth = realDisplayMetrics.widthPixels

    val displayMetrics = DisplayMetrics()
    d.getMetrics(displayMetrics)

    val displayHeight = displayMetrics.heightPixels
    val displayWidth = displayMetrics.widthPixels

    return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
}