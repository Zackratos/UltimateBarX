package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.extension.getNavigationBarHeight
import com.zackratos.ultimatebarx.library.extension.getScreenHeight

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:09 PM
 * @email    : 869649338@qq.com
 * @Describe : 小米 rom
 */
internal class MiuiRom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(activity: FragmentActivity): Boolean {
        return Settings.Global.getInt(activity.contentResolver, "force_fsg_nav_bar", -1) > 0
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun screenIndicatorOn(activity: FragmentActivity): Boolean {
        val navigationBarHeight = activity.getNavigationBarHeight()
        val screenHeight = activity.getScreenHeight()
        return navigationBarHeight > 0 && screenHeight / navigationBarHeight > 30
    }
}