package com.zackratos.ultimatebarx.ultimatebarx

import android.annotation.SuppressLint
import android.os.Build

/**
 * @Author   : zackratos
 * @Date     : 2021/8/23 8:12 下午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
internal val manager: UltimateBarXManager
    get() = UltimateBarXManager.instance

@SuppressLint("AnnotateVersionCheck")
internal val needApply: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT