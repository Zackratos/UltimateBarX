package com.zackratos.ultimatebarx.library.extension

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
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
private const val NO_VIEW_GROUP_MESSAGE = "Use UltimateBarX on Fragment must ensure the Fragment root View is a ViewGroup."

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
    val rootView = requireView()
    if (rootView !is ViewGroup) throw IllegalStateException(NO_VIEW_GROUP_MESSAGE)
    rootView.clipToPadding = false
    rootView.setPadding(
        rootView.paddingLeft,
        (if (fitWindow) getStatusBarHeight() else 0),
        rootView.paddingRight,
        rootView.paddingBottom
    )
    var statusBar: View? = rootView.findViewWithTag(TAG_STATUS_BAR)
    if (statusBar == null) {
        statusBar = View(requireContext())
        statusBar.tag = TAG_STATUS_BAR
        rootView.addView(statusBar, ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
    }
    statusBar.post { statusBar.translationY = -statusBar.top.toFloat() }
    return statusBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Fragment.initNavigationBarView(fitWindow: Boolean): View? {
    if (!requireActivity().navigationBarExist()) return null
    val rootView = requireView()
    if (rootView !is ViewGroup) throw IllegalStateException(NO_VIEW_GROUP_MESSAGE)
    rootView.clipToPadding = false
    rootView.setPadding(
        rootView.paddingLeft,
        rootView.paddingTop,
        rootView.paddingRight,
        (if (fitWindow) getNavigationBarHeight() else 0)
    )
    var navigationBar: View? = rootView.findViewWithTag(TAG_NAVIGATION_BAR)
    if (navigationBar == null) {
        navigationBar = View(requireContext())
        navigationBar.tag = TAG_NAVIGATION_BAR
        rootView.addView(navigationBar, ViewGroup.LayoutParams.MATCH_PARENT, getNavigationBarHeight())
    }
    navigationBar.post { navigationBar.translationY = (rootView.height - navigationBar.bottom).toFloat() }
    return navigationBar
}

private fun Fragment.getStatusBarHeight() = requireActivity().getStatusBarHeight()

private fun Fragment.getNavigationBarHeight() = requireActivity().getNavigationBarHeight()
