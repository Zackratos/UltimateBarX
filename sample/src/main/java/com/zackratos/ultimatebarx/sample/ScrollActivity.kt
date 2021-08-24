package com.zackratos.ultimatebarx.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.zackratos.ultimatebarx.ultimatebarx.addStatusBarTopPadding
import com.zackratos.ultimatebarx.ultimatebarx.getStatusBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import kotlinx.android.synthetic.main.fragment_scroll.*

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/1  10:34 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
class ScrollActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_scroll)
        toolbar.title = "九阴真经"
        statusBar { transparent() }
        toolbar.addStatusBarTopPadding()
        scrollView.setOnScrollChangeListener { _: NestedScrollView?, _, scrollY: Int, _, oldScrollY: Int ->
            val height = imageView.height - toolbar.height
            if (height in (oldScrollY + 1)..scrollY) {
                getStatusBar {
                    light = true
                    lvlColor = Color.GRAY
                }
                toolbar.visibility = View.VISIBLE
            } else if (height in (scrollY + 1)..oldScrollY) {
                getStatusBar {
                    light = false
                }
                toolbar.visibility = View.INVISIBLE
            }
        }
    }

}