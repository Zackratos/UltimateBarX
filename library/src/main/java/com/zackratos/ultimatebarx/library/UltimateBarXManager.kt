package com.zackratos.ultimatebarx.library

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.bean.DefaultStatus
import com.zackratos.ultimatebarx.library.extension.transparentStatusAndNavigationBar
import com.zackratos.ultimatebarx.library.extension.updateNavigationBarView
import com.zackratos.ultimatebarx.library.extension.updateStatusBarView

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/26  12:32 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe : 管理类，主要用于存储 Activity 和 Fragment 当前的状态
 */
internal class UltimateBarXManager private constructor(){
    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = UltimateBarXManager()
    }

    // 保存 StatusBar 的 light 状态
    private val staLightMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存 NavigationBar 的 light 状态
    private val navLightMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存是否已经初始化
    private val actDefMap: MutableMap<String, DefaultStatus> by lazy { ArrayMap<String, DefaultStatus>() }
    // 保存是否已经 AddObserver
    private val addObsMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }




    internal fun applyStatusBar(activity: FragmentActivity, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        val navLight = getNavigationBarLight(activity)
        activity.transparentStatusAndNavigationBar(config.light, navLight)
        updateStatusBarView(activity, config)
        defaultNavigationBar(activity)
        addObserver(activity)
    }

    internal fun applyNavigationBar(activity: FragmentActivity, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        val staLight = getStatusBarLight(activity)
        activity.transparentStatusAndNavigationBar(staLight, config.light)
        updateNavigationBarView(activity, config)
        defaultStatusBar(activity)
        addObserver(activity)
    }

    internal fun applyStatusBar(fragment: Fragment, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        val navLight = getNavigationBarLight(fragment)
        fragment.requireActivity().transparentStatusAndNavigationBar(config.light, navLight)
        val transparentConfig = BarConfig.Builder(UltimateBarX.STATUS_BAR)
            .transparent().light(config.light).build()
        updateStatusBarView(fragment.requireActivity(), transparentConfig)
        updateStatusBarView(fragment, config)
        defaultNavigationBar(fragment.requireActivity())
        addObserver(fragment)
        addObserver(fragment.requireActivity())
    }

    internal fun applyNavigationBar(fragment: Fragment, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        val staLight = getStatusBarLight(fragment)
        fragment.requireActivity().transparentStatusAndNavigationBar(staLight, config.light)
        val transparentConfig = BarConfig.Builder(UltimateBarX.NAVIGATION_BAR)
            .transparent().light(config.light).build()
        updateNavigationBarView(fragment.requireActivity(), transparentConfig)
        updateNavigationBarView(fragment, config)
        defaultStatusBar(fragment.requireActivity())
        addObserver(fragment)
        addObserver(fragment.requireActivity())
    }

    internal fun removeAllData(owner: LifecycleOwner) {
        val key = owner.hashCode().toString()
        actDefMap.remove(key)
        addObsMap.remove(key)
        staLightMap.remove(key)
        navLightMap.remove(key)
    }


    private fun addObserver(owner: LifecycleOwner) {
        if (getAddObserver(owner)) return
        owner.lifecycle.addObserver(UltimateBarXObserver())
        putAddObserver(owner)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateStatusBarView(activity: FragmentActivity, config: BarConfig) {
        activity.updateStatusBarView(config)
        putStatusBarDefault(activity)
        putStatusBarLight(activity, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateNavigationBarView(activity: FragmentActivity, config: BarConfig) {
        activity.updateNavigationBarView(config)
        putNavigationBarDefault(activity)
        putNavigationBarLight(activity, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateStatusBarView(fragment: Fragment, config: BarConfig) {
        fragment.requireView().post { fragment.updateStatusBarView(config) }
        putStatusBarDefault(fragment.requireActivity())
        putStatusBarLight(fragment, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateNavigationBarView(fragment: Fragment, config: BarConfig) {
        fragment.requireView().post { fragment.updateNavigationBarView(config) }
        putNavigationBarDefault(fragment.requireActivity())
        putNavigationBarLight(fragment, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun defaultStatusBar(activity: FragmentActivity) {
        if (getBarDefault(activity).statusBarDefault) return
        updateStatusBarView(activity, BarConfig.DEFAULT_STATUS_BAR_CONFIG)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun defaultNavigationBar(activity: FragmentActivity) {
        if (getBarDefault(activity).navigationBarDefault) return
        updateNavigationBarView(activity, BarConfig.DEFAULT_NAVIGATION_BAR_CONFIG)
    }



    private fun putAddObserver(owner: LifecycleOwner) {
        addObsMap[owner.hashCode().toString()] = true
    }

    private fun getAddObserver(owner: LifecycleOwner): Boolean = addObsMap[owner.hashCode().toString()] ?: false

    private fun putStatusBarDefault(activity: FragmentActivity) {
        actDefMap[activity.hashCode().toString()] = getBarDefault(activity).apply { statusBarDefault = true }
    }

    private fun putNavigationBarDefault(activity: FragmentActivity) {
        actDefMap[activity.hashCode().toString()] = getBarDefault(activity).apply { navigationBarDefault = true }
    }

    private fun getBarDefault(activity: FragmentActivity): DefaultStatus = actDefMap[activity.hashCode().toString()] ?: DefaultStatus()

    private fun putStatusBarLight(owner: LifecycleOwner, light: Boolean) {
        staLightMap[owner.hashCode().toString()] = light
    }

    private fun getStatusBarLight(owner: LifecycleOwner): Boolean = staLightMap[owner.hashCode().toString()] ?: false


    private fun putNavigationBarLight(owner: LifecycleOwner, light: Boolean) {
        navLightMap[owner.hashCode().toString()] = light
    }

    private fun getNavigationBarLight(owner: LifecycleOwner): Boolean = navLightMap[owner.hashCode().toString()] ?: false


}