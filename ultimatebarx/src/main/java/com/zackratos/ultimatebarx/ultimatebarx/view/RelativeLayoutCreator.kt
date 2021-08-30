package com.zackratos.ultimatebarx.ultimatebarx.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.zackratos.navbarexist.navbarexist.navBarExist
import com.zackratos.ultimatebarx.ultimatebarx.extension.fragmentActivity
import com.zackratos.ultimatebarx.ultimatebarx.extension.navigationBarHeight
import com.zackratos.ultimatebarx.ultimatebarx.statusBarHeight

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/29 0:33
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class RelativeLayoutCreator(private val relativeLayout: RelativeLayout, tag: Tag, landscape: Boolean) : BaseCreator(tag, landscape) {

    override fun getStatusBarView(context: Context, fitWindow: Boolean): View {
        var statusBar: View? = relativeLayout.findViewWithTag(tag.statusBarViewTag)
        if (statusBar == null) {
            statusBar = View(context).apply {
                layoutParams = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    statusBarHeight
                ).apply { addRule(RelativeLayout.ALIGN_PARENT_TOP) }
            }
            statusBar.tag = tag.statusBarViewTag
            relativeLayout.addView(statusBar)
        }
        statusBar.layoutParams = (statusBar.layoutParams as RelativeLayout.LayoutParams)
            .apply { topMargin = if (fitWindow) -statusBarHeight else 0 }
        return statusBar
    }

    override fun getNavigationBarView(context: Context, fitWindow: Boolean): View {
        var navigationBar: View? = relativeLayout.findViewWithTag(tag.navigationBarViewTag)
        val activity = context.fragmentActivity
        val navBarHeight = when (activity?.navBarExist) {
            true -> activity.navigationBarHeight
            else -> 0
        }
        val width: Int
        val height: Int
        val rule: Int
        if (landscape) {
            width = navBarHeight
            height = ViewGroup.LayoutParams.MATCH_PARENT
            rule = RelativeLayout.ALIGN_PARENT_RIGHT
        } else {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = navBarHeight
            rule = RelativeLayout.ALIGN_PARENT_BOTTOM
        }
        if (navigationBar == null) {
            navigationBar = View(context).apply {
                layoutParams = RelativeLayout.LayoutParams(width, height).apply { addRule(rule) }
            }
            navigationBar.tag = tag.navigationBarViewTag
            relativeLayout.addView(navigationBar)
        }
        navigationBar.layoutParams = (navigationBar.layoutParams as RelativeLayout.LayoutParams)
            .apply {
                if (landscape) {
                    rightMargin = if (fitWindow) -navBarHeight else 0
                } else {
                    bottomMargin = if (fitWindow) -navBarHeight else 0
                }
            }
        return navigationBar
    }
}