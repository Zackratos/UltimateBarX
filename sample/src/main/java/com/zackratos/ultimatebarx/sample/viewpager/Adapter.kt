package com.zackratos.ultimatebarx.sample.viewpager

import android.graphics.Color
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  1:57 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class Adapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<Fragment> by lazy { SparseArray<Fragment>() }

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = fragments[position]
        if (fragment != null) return fragment
        var color = Color.TRANSPARENT
        var title = ""
        var titleColor = Color.TRANSPARENT
        when (position) {
            0 -> {
                color = Color.RED
                title = "Android"
                titleColor = Color.WHITE
            }
            1 -> {
                color = Color.WHITE
                title = "Album"
                titleColor = Color.BLACK
            }
            2 -> {
                color = Color.BLACK
                title = "Camera"
                titleColor = Color.WHITE
            }
            3 -> {
                color = Color.YELLOW
                title = "Games"
                titleColor = Color.BLACK
            }
        }
        return ViewPagerFragment.newInstance(color, title, titleColor)
            .apply { fragments.put(position, this) }
    }
}