package com.zackratos.ultimatebarx.library.extension

import android.content.Context

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/6/28  7:09 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */

private fun Context.getBarHeight(name: String): Int
        = resources.getDimensionPixelSize(resources.getIdentifier(name, "dimen", "android"))

internal fun Context.getStatusBarHeight() = getBarHeight("status_bar_height")

internal fun Context.getNavigationBarHeight() = getBarHeight("navigation_bar_height")
