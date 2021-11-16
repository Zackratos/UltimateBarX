package com.zackratos.ultimatebarx.sample.adjustresize

import android.os.Bundle
import com.zackratos.ultimatebarx.sample.ViewBindingActivity
import com.zackratos.ultimatebarx.sample.databinding.ActivityAdjustResizeBinding
import com.zackratos.ultimatebarx.ultimatebarx.statusBarOnly

/**
 * @Author   : zackratos
 * @Date     : 2021/8/20 5:26 下午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class AdjustResizeActivity: ViewBindingActivity<ActivityAdjustResizeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarOnly { transparent() }
    }

}