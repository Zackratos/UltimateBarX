package com.zackratos.ultimatebarx.ultimatebarx

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/28  12:47 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class UltimateBarXObserver(private val only: Boolean): LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        UltimateBarXManager.instance.removeAllData(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {
        if (owner !is Fragment) return
        if (only) {
            owner.resetStatusBarOnlyLight()
            return
        }
        owner.resetLight()
    }

    private fun Fragment.resetStatusBarOnlyLight() {
        val staDefault = UltimateBarXManager.instance.getStatusBarDefault(this)
        if (staDefault) {
            val staConfig = statusBarConfig
            val parentStaConfig = requireActivity().statusBarConfig
            if (staConfig.light != parentStaConfig.light) {
                getStatusBarOnly()
            }
        }
    }

    private fun Fragment.resetLight() {
        val staDefault = UltimateBarXManager.instance.getStatusBarDefault(this)
        val navDefault = UltimateBarXManager.instance.getNavigationBarDefault(this)
        if (staDefault) {
            val staConfig = statusBarConfig
            val parentStaConfig = requireActivity().statusBarConfig
            if (staConfig.light != parentStaConfig.light) {
                getStatusBar()
            }
        }
        if (navDefault) {
            val navConfig = navigationBarConfig
            val parentNavConfig = requireActivity().navigationBarConfig
            if (navConfig.light != parentNavConfig.light) {
                getNavigationBar()
            }
        }
    }
}