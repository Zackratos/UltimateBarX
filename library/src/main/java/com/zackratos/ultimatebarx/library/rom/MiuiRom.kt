package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.extension.screenHeight
import com.zackratos.ultimatebarx.library.navigationBarHeight

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
        val navHeight = navigationBarHeight
        val screenHeight = activity.screenHeight
        // 当屏幕高度大于状态栏高度的 30 倍时，就认为开启了手势提示线
        // 否则认为没有开启手势提示线
        return navHeight > 0 && screenHeight / navHeight > 30
    }
}