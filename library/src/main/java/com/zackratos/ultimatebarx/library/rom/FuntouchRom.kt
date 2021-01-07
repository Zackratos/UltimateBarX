package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : 869649338@qq.com
 * @Describe : vivo rom
 */
internal class FuntouchRom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(activity: FragmentActivity): Boolean {
        return Settings.Secure.getInt(activity.contentResolver, "navigation_gesture_on", -1) > 0
    }
}