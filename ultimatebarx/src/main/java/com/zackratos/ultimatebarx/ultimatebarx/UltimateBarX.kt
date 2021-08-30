package com.zackratos.ultimatebarx.ultimatebarx

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.navbarexist.navbarexist.navBarExist
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import com.zackratos.ultimatebarx.ultimatebarx.core.addNavigationBarBottomPadding
import com.zackratos.ultimatebarx.ultimatebarx.core.addStatusBarTopPadding
import com.zackratos.ultimatebarx.ultimatebarx.extension.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.extension.statusBarHeight


fun FragmentActivity.statusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(BarConfig.newInstance(), block)

fun FragmentActivity.navigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(BarConfig.newInstance(), block)

fun FragmentActivity.statusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(BarConfig.newInstance(), block)

fun FragmentActivity.getStatusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(statusBarConfig, block)

fun FragmentActivity.getNavigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(navigationBarConfig, block)

fun FragmentActivity.getStatusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(statusBarConfig, block)

fun Fragment.statusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(BarConfig.newInstance(), block)

fun Fragment.navigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(BarConfig.newInstance(), block)

fun Fragment.statusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(BarConfig.newInstance(), block)

fun Fragment.getStatusBar(block: (BarConfig.() -> Unit)? = null) = statusBar(statusBarConfig, block)

fun Fragment.getNavigationBar(block: (BarConfig.() -> Unit)? = null) = navigationBar(navigationBarConfig, block)

fun Fragment.getStatusBarOnly(block: (BarConfig.() -> Unit)? = null) = statusBarOnly(statusBarConfig, block)

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

fun FragmentActivity.navigationBarHeight(block: ((Int) -> Unit)?) {
    navBarExist {
        val height = if (it) navigationBarHeight else 0
        block?.invoke(height)
    }
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
internal fun FragmentActivity.statusBarOnly(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    if (block != null) {
        config.block()
    }
    applyStatusBarOnly(config)
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

@SuppressLint("NewApi")
internal fun Fragment.statusBarOnly(config: BarConfig, block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    if (block != null) {
        config.block()
    }
    applyStatusBarOnly(config)
}