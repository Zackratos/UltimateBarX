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
import com.zackratos.ultimatebarx.ultimatebarx.operator.Operator
import com.zackratos.ultimatebarx.ultimatebarx.operator.OperatorProvider

/**
 * please use
 * [FragmentActivity.statusBar]
 * [FragmentActivity.navigationBar]
 * [Fragment.statusBar]
 * [Fragment.navigationBar]
 */
@Deprecated("")
class UltimateBarX {
    companion object {

        /**
         * please use [FragmentActivity.statusBar] and [FragmentActivity.navigationBar]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun with(activity: FragmentActivity): Operator = OperatorProvider.create(activity)

        /**
         * please use [Fragment.statusBar] and [Fragment.navigationBar]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun with(fragment: Fragment): Operator = OperatorProvider.create(fragment)

        /**
         * please use [FragmentActivity.getStatusBar] and [FragmentActivity.getNavigationBar]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun get(activity: FragmentActivity): Operator = OperatorProvider.get(activity)

        /**
         * please use [Fragment.getStatusBar] and [Fragment.getNavigationBar]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun get(fragment: Fragment): Operator = OperatorProvider.get(fragment)

        /**
         * please use [FragmentActivity.statusBarConfig]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun getStatusBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.instance.getStatusBarConfig(activity)

        /**
         * please use [FragmentActivity.navigationBarConfig]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun getNavigationBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.instance.getNavigationBarConfig(activity)

        /**
         * please use [Fragment.statusBarConfig]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun getStatusBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.instance.getStatusBarConfig(fragment)

        /**
         * please use [Fragment.navigationBarConfig]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun getNavigationBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.instance.getNavigationBarConfig(fragment)

        /**
         * please use [com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun getStatusBarHeight() = statusBarHeight

        /**
         * please use [com.zackratos.ultimatebarx.ultimatebarx.navigationBarHeight]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun getNavigationBarHeight() = navigationBarHeight

        /**
         * please use [com.zackratos.ultimatebarx.ultimatebarx.addStatusBarTopPadding]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun addStatusBarTopPadding(target: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                target.addStatusBarTopPadding()
        }

        /**
         * please use [com.zackratos.ultimatebarx.ultimatebarx.addNavigationBarBottomPadding]
         */
        @Deprecated("", ReplaceWith(""))
        @JvmStatic
        fun addNavigationBarBottomPadding(target: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                target.addNavigationBarBottomPadding()
        }
    }

}

@SuppressLint("NewApi")
fun FragmentActivity.statusBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

@SuppressLint("NewApi")
fun FragmentActivity.navigationBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

@SuppressLint("NewApi")
fun FragmentActivity.getStatusBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = statusBarConfig
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

@SuppressLint("NewApi")
fun FragmentActivity.getNavigationBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = navigationBarConfig
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

@SuppressLint("NewApi")
fun Fragment.statusBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

@SuppressLint("NewApi")
fun Fragment.navigationBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

@SuppressLint("NewApi")
fun Fragment.getStatusBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = statusBarConfig
    if (block != null) {
        config.block()
    }
    applyStatusBar(config)
}

@SuppressLint("NewApi")
fun Fragment.getNavigationBar(block: (BarConfig.() -> Unit)? = null) {
    if (!needApply) return
    val config = navigationBarConfig
    if (block != null) {
        config.block()
    }
    applyNavigationBar(config)
}

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