package com.zackratos.ultimatebarx.sample.viewpager

import android.graphics.Color
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.sample.R
import com.zackratos.ultimatebarx.sample.TextFragment2

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  1:57 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class ViewPagerAdapter2(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: SparseArray<Fragment> by lazy { SparseArray<Fragment>() }

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var fragment = fragments[position]
        if (fragment != null) return fragment
        fragment = when (position) {
            0 -> TextFragment2.newInstance(Color.BLACK, "Android", Color.WHITE) {
                UltimateBarX.create(UltimateBarX.STATUS_BAR)
                    .fitWindow(true)
                    .bgColor(Color.BLACK)
                    .apply(it)
            }
            1 -> ImageTextFragment.newInstance(R.drawable.yurisa__005) {
                UltimateBarX.create(UltimateBarX.STATUS_BAR)
                    .transparent()
                    .apply(it)
            }
            2 -> TextFragment2.newInstance(Color.BLUE, "Camera", Color.WHITE) {
                UltimateBarX.create(UltimateBarX.STATUS_BAR)
                    .fitWindow(true)
                    .bgColor(Color.BLUE)
                    .apply(it)
            }
            3 -> ImageTextFragment.newInstance(R.drawable.yurisa__004) {
                UltimateBarX.create(UltimateBarX.STATUS_BAR)
                    .fitWindow(false)
                    .bgColorRes(R.color.alphaBlack)
                    .apply(it)
            }
            else -> TextFragment.newInstance(Color.TRANSPARENT, "", Color.TRANSPARENT)
        }
        fragments.put(position, fragment)
        return fragment
    }
}