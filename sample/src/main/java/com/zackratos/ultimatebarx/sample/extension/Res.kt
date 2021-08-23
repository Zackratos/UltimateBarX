package com.zackratos.ultimatebarx.sample.extension

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.zackratos.ultimatebarx.sample.App

/**
 * @Author   : zackratos
 * @Date     : 2021/8/23 7:55 下午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
@ColorInt
fun getResColor(@ColorRes colorRes: Int): Int = ContextCompat.getColor(App.context, colorRes)