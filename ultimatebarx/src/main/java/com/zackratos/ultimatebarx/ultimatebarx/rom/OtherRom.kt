package com.zackratos.ultimatebarx.ultimatebarx.rom

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:01 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class OtherRom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(context: Context): Boolean = false
}