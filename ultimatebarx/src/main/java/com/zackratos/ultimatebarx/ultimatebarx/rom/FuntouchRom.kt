package com.zackratos.ultimatebarx.ultimatebarx.rom

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:10 PM
 * @email    : 869649338@qq.com
 * @Describe : vivo rom
 */
internal class FuntouchRom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(context: Context): Boolean {
        return Settings.Secure.getInt(context.contentResolver, "navigation_gesture_on", -1) > 0
    }
}