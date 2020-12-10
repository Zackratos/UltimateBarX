package com.zackratos.ultimatebarx.sample

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.sample.viewpager.TextFragment
import kotlinx.android.synthetic.main.fragment_text.*

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/8  8:03 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class TextFragment2: TextFragment() {

    companion object {

        fun newInstance(@ColorInt color: Int, title: String,
                        @ColorInt titleColor: Int, block: (Fragment) -> Unit) =
            TextFragment2().apply {
                initArguments(color, title, titleColor)
                statusBarBlock = block
            }
    }

    private var statusBarBlock: ((Fragment) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusBarBlock?.invoke(this)
    }
}