package com.zackratos.ultimatebarx.ultimatebarx

import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import com.zackratos.ultimatebarx.ultimatebarx.core.addNavigationBarBottomPadding
import com.zackratos.ultimatebarx.ultimatebarx.core.addStatusBarTopPadding
import com.zackratos.ultimatebarx.ultimatebarx.extension.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.extension.statusBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.operator.ActivityOperator
import com.zackratos.ultimatebarx.ultimatebarx.operator.FragmentOperator
import com.zackratos.ultimatebarx.ultimatebarx.operator.Operator
import com.zackratos.ultimatebarx.ultimatebarx.operator.OperatorProvider

class UltimateBarX {
    companion object {

        @JvmStatic
        fun with(activity: FragmentActivity): Operator = OperatorProvider.create(activity)

        @JvmStatic
        fun with(fragment: Fragment): Operator = OperatorProvider.create(fragment)

        @JvmStatic
        fun get(activity: FragmentActivity): Operator = OperatorProvider.get(activity)

        @JvmStatic
        fun get(fragment: Fragment): Operator = OperatorProvider.get(fragment)

        @JvmStatic
        fun getStatusBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.instance.getStatusBarConfig(activity)

        @JvmStatic
        fun getNavigationBarConfig(activity: FragmentActivity): BarConfig = UltimateBarXManager.instance.getNavigationBarConfig(activity)

        @JvmStatic
        fun getStatusBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.instance.getStatusBarConfig(fragment)

        @JvmStatic
        fun getNavigationBarConfig(fragment: Fragment): BarConfig = UltimateBarXManager.instance.getNavigationBarConfig(fragment)

        @JvmStatic
        fun getStatusBarHeight() = statusBarHeight

        @JvmStatic
        fun getNavigationBarHeight() = navigationBarHeight

        @JvmStatic
        fun addStatusBarTopPadding(target: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                target.addStatusBarTopPadding()
        }

        @JvmStatic
        fun addNavigationBarBottomPadding(target: View) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                target.addNavigationBarBottomPadding()
        }
    }

}

fun FragmentActivity.statusBar(block: (BarConfig.() -> Unit)? = null) {
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    ActivityOperator.newInstance(this, config).applyStatusBar()
}

fun FragmentActivity.navigationBar(block: (BarConfig.() -> Unit)? = null) {
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    ActivityOperator.newInstance(this, config).applyNavigationBar()
}

fun FragmentActivity.getStatusBar(block: (BarConfig.() -> Unit)? = null) {
    val config = statusBarConfig
    if (block != null) {
        config.block()
    }
    ActivityOperator.newInstance(this, config).applyStatusBar()
}

fun FragmentActivity.getNavigationBar(block: (BarConfig.() -> Unit)? = null) {
    val config = navigationBarConfig
    if (block != null) {
        config.block()
    }
    ActivityOperator.newInstance(this, config).applyNavigationBar()
}

fun Fragment.statusBar(block: (BarConfig.() -> Unit)? = null) {
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    FragmentOperator.newInstance(this, config).applyStatusBar()
}

fun Fragment.navigationBar(block: (BarConfig.() -> Unit)? = null) {
    val config = BarConfig.newInstance()
    if (block != null) {
        config.block()
    }
    FragmentOperator.newInstance(this, config).applyNavigationBar()
}

fun Fragment.getStatusBar(block: (BarConfig.() -> Unit)? = null) {
    val config = statusBarConfig
    if (block != null) {
        config.block()
    }
    FragmentOperator.newInstance(this, config).applyStatusBar()
}

fun Fragment.getNavigationBar(block: (BarConfig.() -> Unit)? = null) {
    val config = navigationBarConfig
    if (block != null) {
        config.block()
    }
    FragmentOperator.newInstance(this, config).applyNavigationBar()
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