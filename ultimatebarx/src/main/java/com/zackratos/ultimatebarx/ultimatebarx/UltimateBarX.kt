package com.zackratos.ultimatebarx.ultimatebarx

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import com.zackratos.ultimatebarx.ultimatebarx.core.addNavigationBarBottomPadding
import com.zackratos.ultimatebarx.ultimatebarx.core.addStatusBarTopPadding
import com.zackratos.ultimatebarx.ultimatebarx.extension.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.extension.statusBarHeight


fun FragmentActivity.statusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(BarConfig.newInstance(), block)

fun FragmentActivity.navigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(BarConfig.newInstance(), block)

fun FragmentActivity.getStatusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(statusBarConfig, block)

fun FragmentActivity.getNavigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(navigationBarConfig, block)

fun Fragment.statusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(BarConfig.newInstance(), block)

fun Fragment.navigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(BarConfig.newInstance(), block)

fun Fragment.getStatusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(statusBarConfig, block)

fun Fragment.getNavigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(navigationBarConfig, block)

val FragmentActivity.statusBarConfig: BarConfig
    get() = manager.getStatusBarConfig(this)

val FragmentActivity.navigationBarConfig: BarConfig
    get() = manager.getNavigationBarConfig(this)

val Fragment.statusBarConfig: BarConfig
    get() = manager.getStatusBarConfig(this)

val Fragment.navigationBarConfig: BarConfig
    get() = manager.getNavigationBarConfig(this)

val statusBarHeight: Int
    get() = manager.context.statusBarHeight

val navigationBarHeight: Int
    get() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
            return 0
        val rom = manager.rom
        val context = manager.context
        if (!rom.navigationBarExist(context)) return 0
        return manager.context.navigationBarHeight
    }

fun View.addStatusBarTopPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addStatusBarTopPadding()
}

fun View.addNavigationBarBottomPadding() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) addNavigationBarBottomPadding()
}

@SuppressLint("NewApi")
internal fun FragmentActivity.statusBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

@SuppressLint("NewApi")
internal fun FragmentActivity.navigationBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

@SuppressLint("NewApi")
internal fun Fragment.statusBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

@SuppressLint("NewApi")
internal fun Fragment.navigationBar(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}