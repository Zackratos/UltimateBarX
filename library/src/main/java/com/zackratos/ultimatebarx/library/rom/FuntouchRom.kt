package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe : vivo rom
 */
internal class FuntouchRom: Rom {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: FragmentActivity): Boolean {
        return Settings.Secure.getInt(activity.contentResolver, "navigation_gesture_on", 0) == 0
    }
}