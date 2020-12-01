package com.zackratos.ultimatebarx.library.extension

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.zackratos.ultimatebarx.library.R
import com.zackratos.ultimatebarx.library.UltimateBarXManager
import com.zackratos.ultimatebarx.library.UltimateBarXObserver
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.view.*

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 17:55
 * @email    : 869649338@qq.com
 * @Describe :
 */
private const val TAG_PARENT = "activity_root_view_parent"
private const val NO_VIEW_GROUP_MESSAGE = "Use UltimateBarX on Fragment must ensure the Fragment root View is a ViewGroup."

private val manager: UltimateBarXManager by lazy { UltimateBarXManager.getInstance() }

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.ultimateBarXInitialization() {
    if (manager.getInitialization(this)) return
    manager.putOriginColor(this)
    barInitialization()
    manager.putInitialization(this)
}


internal fun Fragment.ultimateBarXInitialization() {
    if (manager.getInitialization(this)) return
    val rootView = requireView()
    if (rootView !is ViewGroup) throw IllegalStateException(NO_VIEW_GROUP_MESSAGE)
    rootView.clipToPadding = false
//        putStatusBarLight(fragment, getStatusBarLight(fragment.requireActivity()))
    // 取 Activity 的 NavigationBarLight
    // 防止 Activity 之前设置了 light ，但是被通过 originColor 计算的 light 覆盖掉
    manager.putNavigationBarLight(this, manager.getNavigationBarLight(requireActivity()))
    manager.putInitialization(this)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.updateStatusBar(config: BarConfig) {
    updateStatusBarView(config)
    manager.putStatusBarDefault(this)
    manager.putStatusBarLight(this, config.light)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.updateNavigationBar(config: BarConfig) {
    updateNavigationBarView(config)
    manager.putNavigationBarDefault(this)
    manager.putNavigationBarLight(this, config.light)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun Fragment.updateStatusBar(config: BarConfig) {
    val transparentConfig = BarConfig.newInstance()
        .transparent()
        .light(config.light)
    requireActivity().updateStatusBar(transparentConfig)
    requireView().post { updateStatusBarView(config) }
    manager.putStatusBarLight(this, config.light)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun Fragment.updateNavigationBar(config: BarConfig) {
    val transparentConfig = BarConfig.newInstance()
        .transparent()
        .light(config.light)
    requireActivity().updateNavigationBar(transparentConfig)
    requireView().post { updateNavigationBarView(config) }
    manager.putNavigationBarLight(this, config.light)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.defaultStatusBar() {
    if (manager.getStatusBarDefault(this)) return
    updateStatusBar(BarConfig.newInstance())
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
internal fun FragmentActivity.defaultNavigationBar() {
    if (manager.getNavigationBarDefault(this)) return
    val config = BarConfig.newInstance()
        .color(manager.getOriginColor(this).navigationBarColor)
        .light(manager.getNavigationBarLight(this))
    updateNavigationBar(config)
}

internal fun LifecycleOwner.addObserver() {
    if (manager.getAddObserver(this)) return
    lifecycle.addObserver(UltimateBarXObserver())
    manager.putAddObserver(this)
}


@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun FragmentActivity.barInitialization() {
    val decorView = window?.decorView
    var parentView: ViewGroup? = decorView?.findViewWithTag(TAG_PARENT)
    if (parentView == null) {
        parentView = findViewById(android.R.id.content)
        parentView?.tag = TAG_PARENT
        parentView?.clipToPadding = false
    }
    parentView?.getChildAt(0)?.fitsSystemWindows = false
    barTransparent()
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun FragmentActivity.updateStatusBarView(config: BarConfig) {
    val decorView = window.decorView as FrameLayout?
    val parentView: ViewGroup? = decorView?.findViewWithTag(TAG_PARENT)
    parentView?.setStatusBarPadding(this, config.fitWindow)
    val statusBar = parentView?.getCreator(ActivityTag.getInstance())?.getStatusBarView(this, config.fitWindow)
    statusBar?.updateBackground(config, getColorInt(R.color.colorPrimaryDark))
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun FragmentActivity.updateNavigationBarView(config: BarConfig) {
    if (!manager.rom.navigationBarExist(this)) return
    val decorView = window.decorView as FrameLayout?
    val parentView: ViewGroup? = decorView?.findViewWithTag(TAG_PARENT)
    parentView?.setNavigationBarPadding(this, config.fitWindow)
    val navigationBar = parentView?.getCreator(ActivityTag.getInstance())?.getNavigationBarView(this, config.fitWindow)
    navigationBar?.updateBackground(config)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Fragment.updateStatusBarView(config: BarConfig) {
    val rootView = requireView() as ViewGroup
    rootView.setStatusBarPadding(requireContext(), config.fitWindow)
    val statusBar = rootView.getCreator(FragmentTag.getInstance()).getStatusBarView(requireContext(), config.fitWindow)
    statusBar.updateBackground(config, requireContext().getColorInt(R.color.colorPrimaryDark))
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
private fun Fragment.updateNavigationBarView(config: BarConfig) {
    if (!manager.rom.navigationBarExist(requireActivity())) return
    val rootView = requireView() as ViewGroup
    rootView.setNavigationBarPadding(requireContext(), config.fitWindow)
    val navigationBar = rootView.getCreator(FragmentTag.getInstance()).getNavigationBarView(requireContext(), config.fitWindow)
    navigationBar.updateBackground(config)
}

private fun ViewGroup.getCreator(tag: Tag): Creator {
    return when (this) {
        is FrameLayout -> FrameLayoutCreator(this, tag)
        is RelativeLayout -> RelativeLayoutCreator(this, tag)
        else -> ViewGroupCreator(this, tag)
    }
}

private fun ViewGroup.setStatusBarPadding(context: Context, fitWindow: Boolean) {
//    post {
    setPadding(
        paddingLeft,
        if (fitWindow) context.getStatusBarHeight() else 0,
        paddingRight,
        paddingBottom
    )
//    }
}

private fun ViewGroup.setNavigationBarPadding(context: Context, fitWindow: Boolean) {
//    post {
    setPadding(
        paddingLeft,
        paddingTop,
        paddingRight,
        if (fitWindow) context.getNavigationBarHeight() else 0
    )
//    }
}

private fun View.updateBackground(config: BarConfig, @ColorInt defaultColor: Int = Color.BLACK) {
    when {
        config.drawableRes > 0 -> setBackgroundResource(config.drawableRes)
        config.colorRes > 0 -> setBackgroundColor(context.getColorInt(config.colorRes))
        config.color > Int.MIN_VALUE -> setBackgroundColor(config.color)
        else -> setBackgroundColor(defaultColor)
    }
}


