package com.zackratos.ultimatebarx.sample.extension

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/8  10:26 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
@ColorInt
fun Fragment.getColorInt(@ColorRes colorRes: Int): Int = ContextCompat.getColor(requireActivity(), colorRes)