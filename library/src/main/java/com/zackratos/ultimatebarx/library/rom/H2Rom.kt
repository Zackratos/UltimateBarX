package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/21  1:23 PM
 * @email    : 869649338@qq.com
 * @Describe : 氢 rom
 */
class H2Rom: BaseRom() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun fullScreenGestureOn(activity: FragmentActivity): Boolean {
        // todo 获取氢 rom 是否开启全面屏手势的方法
        return false
    }
}