package com.zackratos.ultimatebarx.library.extension

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.library.R
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.rom.Rom

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
        config.bgColorRes > 0 -> statusBar.setBackgroundColor(ContextCompat.getColor(requireContext(), config.bgColorRes))
        else -> statusBar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
    }
    return statusBar
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun Fragment.updateNavigationBarView(config: BarConfig?, rom: Rom): View? {
    if (config == null) return null
    val navigationBar = initNavigationBarView(config.fitWindow, rom) ?: return null
    when {
        config.bgRes > 0 -> navigationBar.setBackgroundResource(config.bgRes)
        config.bgColor > Int.MIN_VALUE -> navigationBar.setBackgroundColor(config.bgColor)
        config.bgColorRes > 0 -> navigationBar.setBackgroundColor(ContextCompat.getColor(requireContext(), config.bgColorRes))
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
    return when (rootView) {
        is FrameLayout -> getStatusBarViewFl(rootView, fitWindow)
        is RelativeLayout -> getStatusBarViewRl(rootView, fitWindow)
        else -> getStatusBarView(rootView)
    }
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Fragment.initNavigationBarView(fitWindow: Boolean, rom: Rom): View? {
    if (!requireActivity().navigationBarExist(rom)) return null
    val rootView = requireView()
    if (rootView !is ViewGroup) throw IllegalStateException(NO_VIEW_GROUP_MESSAGE)
    rootView.clipToPadding = false
    rootView.setPadding(
        rootView.paddingLeft,
        rootView.paddingTop,
        rootView.paddingRight,
        (if (fitWindow) getNavigationBarHeight() else 0)
    )
    return when (rootView) {
        is FrameLayout -> getNavigationBarViewFl(rootView, fitWindow)
        is RelativeLayout -> getNavigationBarViewRl(rootView, fitWindow)
        else -> getNavigationBarView(rootView)
    }
}

private fun Fragment.getStatusBarViewFl(rootView: ViewGroup, fitWindow: Boolean): View {
    var statusBar: View? = rootView.findViewWithTag(TAG_STATUS_BAR)
    if (statusBar == null) {
        statusBar = createStatusBarViewForFrameLayout()
        statusBar.tag = TAG_STATUS_BAR
        rootView.addView(statusBar)
    }
    statusBar.layoutParams = (statusBar.layoutParams as FrameLayout.LayoutParams)
        .apply { topMargin = if (fitWindow) -getStatusBarHeight() else 0 }
    return statusBar
}

private fun Fragment.getNavigationBarViewFl(rootView: ViewGroup, fitWindow: Boolean): View {
    var navigationBar: View? = rootView.findViewWithTag(TAG_NAVIGATION_BAR)
    if (navigationBar == null) {
        navigationBar = createNavigationBarViewForFrameLayout()
        navigationBar.tag = TAG_NAVIGATION_BAR
        rootView.addView(navigationBar)
    }
    navigationBar.layoutParams = (navigationBar.layoutParams as FrameLayout.LayoutParams)
        .apply { bottomMargin = if (fitWindow) -getNavigationBarHeight() else 0 }
    return navigationBar
}

private fun Fragment.getStatusBarViewRl(rootView: ViewGroup, fitWindow: Boolean): View {
    var statusBar: View? = rootView.findViewWithTag(TAG_STATUS_BAR)
    if (statusBar == null) {
        statusBar = createStatusBarViewForRelativeLayout()
        statusBar.tag = TAG_STATUS_BAR
        rootView.addView(statusBar)
    }
    statusBar.layoutParams = (statusBar.layoutParams as RelativeLayout.LayoutParams)
        .apply { topMargin = if (fitWindow) -getStatusBarHeight() else 0 }
    return statusBar
}

private fun Fragment.getNavigationBarViewRl(rootView: ViewGroup, fitWindow: Boolean): View {
    var navigationBar: View? = rootView.findViewWithTag(TAG_NAVIGATION_BAR)
    if (navigationBar == null) {
        navigationBar = createNavigationBarViewForRelativeLayout()
        navigationBar.tag = TAG_NAVIGATION_BAR
        rootView.addView(navigationBar)
    }
    navigationBar.layoutParams = (navigationBar.layoutParams as RelativeLayout.LayoutParams)
        .apply { bottomMargin = if (fitWindow) -getNavigationBarHeight() else 0 }
    return navigationBar
}

private fun Fragment.getStatusBarView(rootView: ViewGroup): View {
    var statusBar: View? = rootView.findViewWithTag(TAG_STATUS_BAR)
    if (statusBar == null) {
        statusBar = View(requireContext())
        statusBar.tag = TAG_STATUS_BAR
        rootView.addView(statusBar, ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
    }
    statusBar.post { statusBar.translationY = -statusBar.top.toFloat() }
    // 防止因为 setPadding 导致 navigationBar 的 bottom 变化
    val navigationBar: View? = rootView.findViewWithTag(TAG_NAVIGATION_BAR)
    navigationBar?.post { navigationBar.translationY = (rootView.height - navigationBar.bottom).toFloat() }
    return statusBar
}

private fun Fragment.getNavigationBarView(rootView: ViewGroup): View {
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

private fun Fragment.createStatusBarViewForFrameLayout(): View = requireActivity().createStatusBarView()

private fun Fragment.createNavigationBarViewForFrameLayout(): View = requireActivity().createNavigationBarView()

private fun Fragment.createStatusBarViewForRelativeLayout(): View =
    View(requireContext()).apply {
        layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            getStatusBarHeight()
        ).apply { addRule(RelativeLayout.ALIGN_PARENT_TOP) }
    }

private fun Fragment.createNavigationBarViewForRelativeLayout(): View =
    View(requireContext()).apply {
        layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            getNavigationBarHeight()
        ).apply { addRule(RelativeLayout.ALIGN_PARENT_BOTTOM) }
    }