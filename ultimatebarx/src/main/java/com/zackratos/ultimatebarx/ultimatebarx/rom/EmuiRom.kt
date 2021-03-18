package com.zackratos.ultimatebarx.ultimatebarx.rom

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : 869649338@qq.com
 * @Describe : 华为 rom
 */
internal class EmuiRom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun fullScreenGestureOn(context: Context): Boolean {
        return Settings.Global.getInt(context.contentResolver, "navigationbar_is_min", -1) > 0
    }
}