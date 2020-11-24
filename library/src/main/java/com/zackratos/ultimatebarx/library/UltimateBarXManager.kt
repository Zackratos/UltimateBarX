package com.zackratos.ultimatebarx.library

import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.zackratos.ultimatebarx.library.bean.BarColor
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.extension.*
import com.zackratos.ultimatebarx.library.rom.Rom

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

    private val rom: Rom by lazy { getRom() }

    // 保存 StatusBar 的 light 状态
    private val staLightMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存 NavigationBar 的 light 状态
    private val navLightMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存 Activity 的 StatusBar 是否设置过
    private val staDefMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存 Activity 的 NavigationBar 是否设置过
    private val navDefMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存是否已经 AddObserver
    private val addObsMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存是否已经初始化
    private val initializationMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存初始 StatusBar 和 NavigationBar 颜色
    private val originColor: MutableMap<String, BarColor> by lazy { ArrayMap<String, BarColor>() }




    internal fun applyStatusBar(activity: FragmentActivity, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        initialization(activity)
        val navLight = getNavigationBarLight(activity)
        activity.barLight(config.light, navLight)
        updateStatusBarView(activity, config)
        defaultNavigationBar(activity)
        addObserver(activity)
    }

    internal fun applyNavigationBar(activity: FragmentActivity, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        initialization(activity)
        val staLight = getStatusBarLight(activity)
        activity.barLight(staLight, config.light)
        updateNavigationBarView(activity, config)
        defaultStatusBar(activity)
        addObserver(activity)
    }

    internal fun applyStatusBar(fragment: Fragment, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        initialization(fragment.requireActivity())
        initialization(fragment)
        val navLight = getNavigationBarLight(fragment)
        fragment.requireActivity().barLight(config.light, navLight)
        updateStatusBarView(fragment, config)
        defaultNavigationBar(fragment.requireActivity())
        addObserver(fragment)
        addObserver(fragment.requireActivity())
    }

    internal fun applyNavigationBar(fragment: Fragment, config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        initialization(fragment.requireActivity())
        initialization(fragment)
        val staLight = getStatusBarLight(fragment)
        fragment.requireActivity().barLight(staLight, config.light)
        updateNavigationBarView(fragment, config)
        defaultStatusBar(fragment.requireActivity())
        addObserver(fragment)
        addObserver(fragment.requireActivity())
    }

    internal fun removeAllData(owner: LifecycleOwner) {
        val key = owner.hashCode().toString()
        staDefMap.remove(key)
        navDefMap.remove(key)
        addObsMap.remove(key)
        staLightMap.remove(key)
        navLightMap.remove(key)
        initializationMap.remove(key)
        originColor.remove(key)
    }


    private fun addObserver(owner: LifecycleOwner) {
        if (getAddObserver(owner)) return
        owner.lifecycle.addObserver(UltimateBarXObserver())
        putAddObserver(owner)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initialization(activity: FragmentActivity) {
        if (getInitialization(activity)) return
        putOriginColor(activity)
        activity.barInitialization()
        putInitialization(activity)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initialization(fragment: Fragment) {
        if (getInitialization(fragment)) return
//        putStatusBarLight(fragment, getStatusBarLight(fragment.requireActivity()))
        // 取 Activity 的 NavigationBarLight
        // 防止 Activity 之前设置了 light ，但是被通过 originColor 计算的 light 覆盖掉
        putNavigationBarLight(fragment, getNavigationBarLight(fragment.requireActivity()))
        putInitialization(fragment)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateStatusBarView(activity: FragmentActivity, config: BarConfig) {
        activity.updateStatusBarView(config)
        putStatusBarDefault(activity)
        putStatusBarLight(activity, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateNavigationBarView(activity: FragmentActivity, config: BarConfig) {
        activity.updateNavigationBarView(config, rom)
        putNavigationBarDefault(activity)
        putNavigationBarLight(activity, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateStatusBarView(fragment: Fragment, config: BarConfig) {
        val transparentConfig = BarConfig.Builder(UltimateBarX.STATUS_BAR)
            .transparent().light(config.light).build()
        updateStatusBarView(fragment.requireActivity(), transparentConfig)
        fragment.requireView().post { fragment.updateStatusBarView(config) }
        putStatusBarLight(fragment, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun updateNavigationBarView(fragment: Fragment, config: BarConfig) {
        val transparentConfig = BarConfig.Builder(UltimateBarX.NAVIGATION_BAR)
            .transparent().light(config.light).build()
        updateNavigationBarView(fragment.requireActivity(), transparentConfig)
        fragment.requireView().post { fragment.updateNavigationBarView(config, rom) }
        putNavigationBarLight(fragment, config.light)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun defaultStatusBar(activity: FragmentActivity) {
        if (getStatusBarDefault(activity)) return
        updateStatusBarView(activity, BarConfig.DEFAULT_STATUS_BAR_CONFIG)
//        val config = BarConfig.Builder(UltimateBarX.STATUS_BAR)
//            .bgColor(getOriginColor(activity).statusBarColor)
//            .light(getStatusBarLight(activity))
//            .build()
//        updateStatusBarView(activity, config)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun defaultNavigationBar(activity: FragmentActivity) {
        if (getNavigationBarDefault(activity)) return
        val config = BarConfig.Builder(UltimateBarX.NAVIGATION_BAR)
            .bgColor(getOriginColor(activity).navigationBarColor)
            .light(getNavigationBarLight(activity))
            .build()
        updateNavigationBarView(activity, config)
    }



    private fun putAddObserver(owner: LifecycleOwner) {
        addObsMap[owner.hashCode().toString()] = true
    }

    private fun getAddObserver(owner: LifecycleOwner): Boolean = addObsMap[owner.hashCode().toString()] ?: false

    private fun putStatusBarDefault(owner: LifecycleOwner) {
        staDefMap[owner.hashCode().toString()] = true
    }

    private fun putNavigationBarDefault(owner: LifecycleOwner) {
        navDefMap[owner.hashCode().toString()] = true
    }

    private fun getStatusBarDefault(owner: LifecycleOwner) = staDefMap[owner.hashCode().toString()] ?: false

    private fun getNavigationBarDefault(owner: LifecycleOwner) = navDefMap[owner.hashCode().toString()] ?: false

    private fun putStatusBarLight(owner: LifecycleOwner, light: Boolean) {
        staLightMap[owner.hashCode().toString()] = light
    }

    private fun getStatusBarLight(owner: LifecycleOwner): Boolean = staLightMap[owner.hashCode().toString()] ?: false


    private fun putNavigationBarLight(owner: LifecycleOwner, light: Boolean) {
        navLightMap[owner.hashCode().toString()] = light
    }

    private fun getNavigationBarLight(owner: LifecycleOwner): Boolean = navLightMap[owner.hashCode().toString()] ?: false

    private fun getInitialization(owner: LifecycleOwner): Boolean = initializationMap[owner.hashCode().toString()] ?: false

    private fun putInitialization(owner: LifecycleOwner) {
        initializationMap[owner.hashCode().toString()] = true
    }

    private fun getOriginColor(activity: FragmentActivity): BarColor = originColor[activity.hashCode().toString()] ?: BarColor(Color.TRANSPARENT, Color.TRANSPARENT)

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun putOriginColor(activity: FragmentActivity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        val statusBarColor = activity.window?.statusBarColor ?: Color.TRANSPARENT
        val navigationBarColor = activity.window?.navigationBarColor ?: Color.TRANSPARENT
        originColor[activity.hashCode().toString()] = BarColor(statusBarColor, navigationBarColor)
//        putStatusBarLight(activity, calculateLight(statusBarColor))
        putNavigationBarLight(activity, calculateLight(navigationBarColor))
    }

    private fun calculateLight(@ColorInt color: Int) = color > (Color.BLACK + Color.WHITE / 2)


}