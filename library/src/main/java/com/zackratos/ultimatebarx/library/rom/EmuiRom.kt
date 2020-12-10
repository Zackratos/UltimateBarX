package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : 869649338@qq.com
 * @Describe : 华为 rom
 */
internal class EmuiRom: Rom {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: FragmentActivity): Boolean {
        return Settings.Global.getInt(activity.contentResolver, "navigationbar_is_min", 0) == 0
    }
}