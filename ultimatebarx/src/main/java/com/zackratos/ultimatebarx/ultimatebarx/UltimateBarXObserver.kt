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
internal class UltimateBarXObserver(val only: Boolean): LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        UltimateBarXManager.instance.removeAllData(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {
        if (owner !is Fragment) return
        if (only) {
            val staDefault = UltimateBarXManager.instance.getStatusBarDefault(owner)
            if (staDefault) {
                owner.getStatusBarOnly()
            }
            return
        }
        val staDefault = UltimateBarXManager.instance.getStatusBarDefault(owner)
        val navDefault = UltimateBarXManager.instance.getNavigationBarDefault(owner)
        if (staDefault) {
            owner.getStatusBar()
        }
        if (navDefault) {
            owner.getNavigationBar()
        }
    }
}