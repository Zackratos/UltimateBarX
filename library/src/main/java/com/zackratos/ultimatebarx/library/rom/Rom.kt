package com.zackratos.ultimatebarx.library.rom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  7:55 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal interface Rom {

    companion object {
        const val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
        const val KEY_VERSION_EMUI = "ro.build.version.emui"
        const val KEY_VERSION_OPPO = "ro.build.version.opporom"
        const val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
        const val KEY_VERSION_VIVO = "ro.vivo.os.version"
        const val KEY_VERSION_H2 = "ro.rom.version"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun navigationBarExist(activity: FragmentActivity): Boolean

}