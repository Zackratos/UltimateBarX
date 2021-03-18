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
internal class UltimateBarXObserver: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        UltimateBarXManager.getInstance().removeAllData(owner)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner) {
        if (owner is Fragment) {
            val staDefault = UltimateBarXManager.getInstance().getStatusBarDefault(owner)
            val navDefault = UltimateBarXManager.getInstance().getNavigationBarDefault(owner)
            if (staDefault) {
                UltimateBarX.get(owner).applyStatusBar()
            }
            if (navDefault) {
                UltimateBarX.get(owner).applyNavigationBar()
            }
        }
    }
}