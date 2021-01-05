package com.zackratos.ultimatebarx.sample

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.sample.R
import kotlinx.android.synthetic.main.fragment_image_text.*

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/8  6:57 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class ImageTextFragment: Fragment(R.layout.fragment_image_text) {

    companion object {
        private const val IMAGE = "image"
        fun newInstance(@DrawableRes imageRes: Int, block: ((Fragment) -> Unit)? = null) =
            ImageTextFragment().apply {
                arguments = Bundle().apply { putInt(IMAGE, imageRes) }
                statusBarBlock = block
            }
    }

    private val imageRes by lazy { arguments?.getInt(IMAGE) ?: 0 }

    private var statusBarBlock: ((Fragment) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topImage.setImageResource(imageRes)
        statusBarBlock?.invoke(this)
    }

}