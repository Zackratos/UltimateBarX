package com.zackratos.ultimatebarx.library.extension

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewParent
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.library.R
import com.zackratos.ultimatebarx.library.bean.BarConfig

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/8  10:28 AM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */

private const val TAG_STATUS_BAR = "fragment_status_bar"
private const val TAG_NAVIGATION_BAR = "fragment_navigation_bar"
private const val NOT_FRAMELAYOUT_MESSAGE = "Use UltimateBarX on Fragment must ensure the Fragment parent View is a FrameLayout, " +
        "You can Use UltimateBarX on Activity instead of it."

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun Fragment.updateStatusBarView(config: BarConfig?): View? {
    if (config == null) return null
    val statusBar = initStatusBarView(config.fitWindow)
    when {
        config.bgRes > 0 -> statusBar.setBackgroundResource(config.bgRes)
        config.bgColor > Int.MIN_VALUE -> statusBar.setBackgroundColor(config.bgColor)
        config.bgColorRes > 0 -> statusBar.setBackgroundColor(ContextCompat.getColor(requireActivity(), config.bgColorRes))
        else -> statusBar.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark))
    }
    return statusBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun Fragment.updateNavigationBarView(config: BarConfig?): View? {
    if (config == null) return null
    val navigationBar = initNavigationBarView(config.fitWindow) ?: return null
    when {
        config.bgRes > 0 -> navigationBar.setBackgroundResource(config.bgRes)
        config.bgColor > Int.MIN_VALUE -> navigationBar.setBackgroundColor(config.bgColor)
        config.bgColorRes > 0 -> navigationBar.setBackgroundColor(ContextCompat.getColor(requireActivity(), config.bgColorRes))
        else -> navigationBar.setBackgroundColor(Color.BLACK)
    }
    return navigationBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Fragment.initStatusBarView(fitWindow: Boolean): View {
    val parentView: ViewParent? = requireView().parent
    if (parentView !is FrameLayout) {
        throw IllegalStateException(NOT_FRAMELAYOUT_MESSAGE)
    }
    parentView.clipToPadding = false
    parentView.setPadding(0, if (fitWindow) getStatusBarHeight() else 0, 0, parentView.paddingBottom)
    var statusBar: View? = parentView.findViewWithTag(TAG_STATUS_BAR)
    if (statusBar == null) {
        statusBar = requireActivity().createStatusBarView()
        statusBar.tag = TAG_STATUS_BAR
        parentView.addView(statusBar)
    }
    statusBar.layoutParams = (statusBar.layoutParams as FrameLayout.LayoutParams)
        .apply { topMargin = if (fitWindow) -getStatusBarHeight() else 0 }
    return statusBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Fragment.initNavigationBarView(fitWindow: Boolean): View? {
    if (!requireActivity().navigationBarExist()) return null
    val parentView: ViewParent? = requireView().parent
    if (parentView !is FrameLayout) {
        throw IllegalStateException(NOT_FRAMELAYOUT_MESSAGE)
    }
    parentView.clipToPadding = false
    parentView.setPadding(0, parentView.paddingTop, 0, if (fitWindow) getNavigationBarHeight() else 0)
    var navigationBar: View? = parentView.findViewWithTag(TAG_NAVIGATION_BAR)
    if (navigationBar == null) {
        navigationBar = requireActivity().createNavigationBarView()
        navigationBar.tag = TAG_NAVIGATION_BAR
        parentView.addView(navigationBar)
    }
    navigationBar.layoutParams = (navigationBar.layoutParams as FrameLayout.LayoutParams)
        .apply { bottomMargin = if (fitWindow) -getNavigationBarHeight() else 0 }
    return navigationBar
}

private fun Fragment.getStatusBarHeight() = requireActivity().getStatusBarHeight()

private fun Fragment.getNavigationBarHeight() = requireActivity().getNavigationBarHeight()
