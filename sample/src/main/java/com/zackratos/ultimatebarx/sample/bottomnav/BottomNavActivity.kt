package com.zackratos.ultimatebarx.sample.bottomnav

import android.os.Bundle
import com.zackratos.ultimatebarx.sample.R
import com.zackratos.ultimatebarx.sample.ViewBindingActivity
import com.zackratos.ultimatebarx.sample.databinding.ActivityBottomNavBinding
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX

/**
 * @Author   : zackratos
 * @Date     : 2021/8/20 10:46 上午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class BottomNavActivity: ViewBindingActivity<ActivityBottomNavBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UltimateBarX.with(this).transparent().light(true).applyStatusBar()
        binding.ivYurisa.setImageResource(R.drawable.yurisa__001)
        binding.bottomNav.setOnNavigationItemSelectedListener {
            val image = when (it.itemId) {
                R.id.item_album -> R.drawable.yurisa__001
                R.id.item_android -> R.drawable.yurisa__002
                R.id.item_camera -> R.drawable.yurisa__003
                R.id.item_games -> R.drawable.yurisa__004
                else -> R.drawable.yurisa__001
            }
            binding.ivYurisa.setImageResource(image)
            return@setOnNavigationItemSelectedListener true
        }
    }

}