package com.zackratos.ultimatebarx.library

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.collection.ArrayMap
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.bean.DefaultStatus
import com.zackratos.ultimatebarx.library.extension.updateNavigationBarView
import com.zackratos.ultimatebarx.library.extension.transparentStatusAndNavigationBar
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
    private val actStaLightMap: MutableMap<FragmentActivity, Boolean> by lazy { ArrayMap<FragmentActivity, Boolean>() }
    // 保存 NavigationBar 的 light 状态
    private val actNavLightMap: MutableMap<FragmentActivity, Boolean> by lazy { ArrayMap<FragmentActivity, Boolean>() }
    // 保存是否已经初始化
    private val actDefMap: MutableMap<FragmentActivity, DefaultStatus> by lazy { ArrayMap<FragmentActivity, DefaultStatus>() }
    // 保存是否已经 AddObserver
    private val actAddObsMap: MutableMap<FragmentActivity, Boolean> by lazy { ArrayMap<FragmentActivity, Boolean>() }




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

    internal fun removeAllData(activity: FragmentActivity) {
        actDefMap.remove(activity)
        actAddObsMap.remove(activity)
        actStaLightMap.remove(activity)
        actNavLightMap.remove(activity)
    }

    private fun addObserver(activity: FragmentActivity) {
        if (getAddObserver(activity)) return
        activity.lifecycle.addObserver(UltimateBarXObserver())
        putAddObserver(activity)
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
    private fun defaultStatusBar(activity: FragmentActivity) {
        if (getBarDefault(activity).statusBarDefault) return
        updateStatusBarView(activity, BarConfig.DEFAULT_STATUS_BAR_CONFIG)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun defaultNavigationBar(activity: FragmentActivity) {
        if (getBarDefault(activity).navigationBarDefault) return
        updateNavigationBarView(activity, BarConfig.DEFAULT_NAVIGATION_BAR_CONFIG)
    }

    private fun putAddObserver(activity: FragmentActivity) {
        actAddObsMap[activity] = true
    }

    private fun getAddObserver(activity: FragmentActivity): Boolean = actAddObsMap[activity] ?: false

    private fun putStatusBarDefault(activity: FragmentActivity) {
        actDefMap[activity] = getBarDefault(activity).apply { statusBarDefault = true }
    }

    private fun putNavigationBarDefault(activity: FragmentActivity) {
        actDefMap[activity] = getBarDefault(activity).apply { navigationBarDefault = true }
    }

    private fun getBarDefault(activity: FragmentActivity): DefaultStatus = actDefMap[activity] ?: DefaultStatus()

    private fun putStatusBarLight(activity: FragmentActivity, light: Boolean) {
        actStaLightMap[activity] = light
    }

    private fun getStatusBarLight(activity: FragmentActivity): Boolean = actStaLightMap[activity] ?: false


    private fun putNavigationBarLight(activity: FragmentActivity, light: Boolean) {
        actNavLightMap[activity] = light
    }

    private fun getNavigationBarLight(activity: FragmentActivity): Boolean = actNavLightMap[activity] ?: false



}