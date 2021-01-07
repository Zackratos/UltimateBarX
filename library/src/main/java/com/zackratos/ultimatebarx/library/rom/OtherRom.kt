package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:01 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class OtherRom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(activity: FragmentActivity): Boolean = false
}