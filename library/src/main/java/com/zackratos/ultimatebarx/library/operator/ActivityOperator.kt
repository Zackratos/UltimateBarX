package com.zackratos.ultimatebarx.library.operator

import android.os.Build
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.extension.*

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/27 0:34
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class ActivityOperator(val activity: FragmentActivity): BaseOperator() {

    override fun applyStatusBar(config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        activity.ultimateBarXInitialization()
        val navLight = manager.getNavigationBarLight(activity)
        activity.barLight(config.light, navLight)
        activity.updateStatusBar(config)
        activity.defaultNavigationBar()
        activity.addObserver()
    }

    override fun applyNavigationBar(config: BarConfig) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        activity.ultimateBarXInitialization()
        val staLight = manager.getStatusBarLight(activity)
        activity.barLight(staLight, config.light)
        activity.updateNavigationBar(config)
        activity.defaultStatusBar()
        activity.addObserver()
    }

}