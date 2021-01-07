package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.extension.commonNavigationBarExist

/**
 * @Author   : zhangwenchao
 * @Date     : 2021/1/7  4:56 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
abstract class BaseRom: Rom {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: FragmentActivity): Boolean {
        if (fullScreenGestureOn(activity)) {
            return false
        }
        return activity.commonNavigationBarExist()
    }

    // 是否开启了全面屏手势
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    protected abstract fun fullScreenGestureOn(activity: FragmentActivity): Boolean

}