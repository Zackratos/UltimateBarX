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
import com.zackratos.ultimatebarx.library.extension.getRom
import com.zackratos.ultimatebarx.library.rom.Rom
import java.lang.reflect.Field

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

    internal val rom: Rom by lazy { getRom() }

    internal val fragmentViewFiled: Field by lazy { Fragment::class.java.getDeclaredField("mView").apply { isAccessible = true } }
    // 保存 Activity 的 StatusBar 是否设置过
    private val staDefMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存 Activity 的 NavigationBar 是否设置过
    private val navDefMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存是否已经 AddObserver
    private val addObsMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存是否已经初始化
    private val initializationMap: MutableMap<String, Boolean> by lazy { ArrayMap<String, Boolean>() }
    // 保存初始 StatusBar 和 NavigationBar 颜色
    private val originColorMap: MutableMap<String, BarColor> by lazy { ArrayMap<String, BarColor>() }

    private val staConfigMap: MutableMap<String, BarConfig> by lazy { ArrayMap<String, BarConfig>() }

    private val navConfigMap: MutableMap<String, BarConfig> by lazy { ArrayMap<String, BarConfig>() }



    internal fun removeAllData(owner: LifecycleOwner) {
        val key = owner.hashCode().toString()
        staDefMap.remove(key)
        navDefMap.remove(key)
        addObsMap.remove(key)
        initializationMap.remove(key)
        originColorMap.remove(key)
        staConfigMap.remove(key)
        navConfigMap.remove(key)
    }


    internal fun putAddObserver(owner: LifecycleOwner) {
        addObsMap[owner.hashCode().toString()] = true
    }

    internal fun getAddObserver(owner: LifecycleOwner): Boolean = addObsMap[owner.hashCode().toString()] ?: false

    internal fun putStatusBarDefault(owner: LifecycleOwner) {
        staDefMap[owner.hashCode().toString()] = true
    }

    internal fun putNavigationBarDefault(owner: LifecycleOwner) {
        navDefMap[owner.hashCode().toString()] = true
    }

    internal fun getStatusBarDefault(owner: LifecycleOwner) = staDefMap[owner.hashCode().toString()] ?: false

    internal fun getNavigationBarDefault(owner: LifecycleOwner) = navDefMap[owner.hashCode().toString()] ?: false

    internal fun getInitialization(owner: LifecycleOwner): Boolean = initializationMap[owner.hashCode().toString()] ?: false

    internal fun putInitialization(owner: LifecycleOwner) {
        initializationMap[owner.hashCode().toString()] = true
    }

    internal fun getOriginColor(activity: FragmentActivity): BarColor = originColorMap[activity.hashCode().toString()] ?: BarColor()

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    internal fun putOriginColor(activity: FragmentActivity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return
        val statusBarColor = activity.window?.statusBarColor ?: Color.TRANSPARENT
        val navigationBarColor = activity.window?.navigationBarColor ?: Color.TRANSPARENT
        originColorMap[activity.hashCode().toString()] = BarColor(statusBarColor, navigationBarColor)
//        putStatusBarLight(activity, calculateLight(statusBarColor))
//        putNavigationBarLight(activity, calculateLight(navigationBarColor))
        val navConfig = getNavigationBarConfig(activity)
        navConfig.light = calculateLight(navigationBarColor)
        putNavigationBarConfig(activity, navConfig)
    }

    private fun calculateLight(@ColorInt color: Int) = color > (Color.BLACK + Color.WHITE / 2)

    internal fun putStatusBarConfig(owner: LifecycleOwner, config: BarConfig) {
        staConfigMap[owner.hashCode().toString()] = config
    }

    internal fun getStatusBarConfig(owner: LifecycleOwner): BarConfig = staConfigMap[owner.hashCode().toString()] ?: BarConfig.newInstance()

    internal fun putNavigationBarConfig(owner: LifecycleOwner, config: BarConfig) {
        navConfigMap[owner.hashCode().toString()] = config
    }

    internal fun getNavigationBarConfig(owner: LifecycleOwner): BarConfig =
        navConfigMap[owner.hashCode().toString()] ?: BarConfig.newInstance()

}