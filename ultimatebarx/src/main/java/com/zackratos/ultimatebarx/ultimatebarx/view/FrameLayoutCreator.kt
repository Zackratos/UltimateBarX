package com.zackratos.ultimatebarx.ultimatebarx.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import com.zackratos.ultimatebarx.ultimatebarx.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 19:37
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class FrameLayoutCreator(private val frameLayout: FrameLayout, tag: Tag, landscape: Boolean): BaseCreator(tag, landscape) {

    override fun getStatusBarView(context: Context, fitWindow: Boolean): View {
        var statusBar: View? = frameLayout.findViewWithTag(tag.statusBarViewTag)
        if (statusBar == null) {
            statusBar = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    statusBarHeight
                ).apply { gravity = Gravity.TOP }
            }
            statusBar.tag = tag.statusBarViewTag
            frameLayout.addView(statusBar)
        }
        statusBar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                statusBar.layoutParams = (statusBar.layoutParams as FrameLayout.LayoutParams)
                    .apply { topMargin = if (fitWindow) -statusBarHeight else 0 }
                statusBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
        return statusBar
    }

    override fun getNavigationBarView(context: Context, fitWindow: Boolean): View {
        var navigationBar: View? = frameLayout.findViewWithTag(tag.navigationBarViewTag)
        val width: Int
        val height: Int
        val gravity: Int
        if (landscape) {
            width = navigationBarHeight
            height = ViewGroup.LayoutParams.MATCH_PARENT
            gravity = Gravity.RIGHT
        } else {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = navigationBarHeight
            gravity = Gravity.BOTTOM
        }
        if (navigationBar == null) {
            navigationBar = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(width, height).apply { this.gravity = gravity }
            }
            navigationBar.tag = tag.navigationBarViewTag
            frameLayout.addView(navigationBar)
        }
        navigationBar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                navigationBar.layoutParams = (navigationBar.layoutParams as FrameLayout.LayoutParams)
                    .apply {
                        if (landscape) {
                            rightMargin = if (fitWindow) -navigationBarHeight else 0
                        } else {
                            bottomMargin = if (fitWindow) -navigationBarHeight else 0
                        }
                    }
                navigationBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
        return navigationBar
    }
}