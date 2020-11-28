package com.zackratos.ultimatebarx.library.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.zackratos.ultimatebarx.library.extension.getNavigationBarHeight
import com.zackratos.ultimatebarx.library.extension.getStatusBarHeight

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 19:37
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class FrameLayoutCreator(private val frameLayout: FrameLayout, tag: Tag): BaseCreator(tag) {

    override fun getStatusBarView(context: Context, fitWindow: Boolean): View {
        var statusBar: View? = frameLayout.findViewWithTag(tag.statusBarViewTag())
        if (statusBar == null) {
            statusBar = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    context.getStatusBarHeight()
                ).apply { gravity = Gravity.TOP }
            }
            statusBar.tag = tag.statusBarViewTag()
            frameLayout.addView(statusBar)
        }
        statusBar.layoutParams = (statusBar.layoutParams as FrameLayout.LayoutParams)
            .apply { topMargin = if (fitWindow) -context.getStatusBarHeight() else 0 }
        return statusBar
    }

    override fun getNavigationBarView(context: Context, fitWindow: Boolean): View {
        var navigationBar: View? = frameLayout.findViewWithTag(tag.navigationBarViewTag())
        if (navigationBar == null) {
            navigationBar = View(context).apply {
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    context.getNavigationBarHeight()
                ).apply { gravity = Gravity.BOTTOM }
            }
            navigationBar.tag = tag.navigationBarViewTag()
            frameLayout.addView(navigationBar)
        }
        navigationBar.layoutParams = (navigationBar.layoutParams as FrameLayout.LayoutParams)
            .apply { bottomMargin = if (fitWindow) -context.getNavigationBarHeight() else 0 }
        return navigationBar
    }
}