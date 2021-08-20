package com.zackratos.ultimatebarx.ultimatebarx.extension

import android.view.View
import android.view.ViewGroup

/**
 * @Author   : zackratos
 * @Date     : 2021/8/20 11:28 上午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
internal val View.children: List<View>
    get() {
        val views = arrayListOf<View>()
        if (this is ViewGroup) {
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                views.add(child)
                views.addAll(child.children)
            }
        }
        return views
    }
