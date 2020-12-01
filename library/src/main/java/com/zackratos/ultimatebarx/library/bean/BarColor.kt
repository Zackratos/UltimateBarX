package com.zackratos.ultimatebarx.library.bean

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/23  6:15 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe : 主要用来封装 状态栏和导航栏的初始颜色
 */
internal class BarColor(@ColorInt val statusBarColor: Int = Color.TRANSPARENT, @ColorInt val navigationBarColor: Int = Color.BLACK)