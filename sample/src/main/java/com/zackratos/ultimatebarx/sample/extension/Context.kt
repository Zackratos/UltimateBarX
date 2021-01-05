package com.zackratos.ultimatebarx.sample.extension

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/30  11:26 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
@ColorInt
fun Context.getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(this, colorRes)