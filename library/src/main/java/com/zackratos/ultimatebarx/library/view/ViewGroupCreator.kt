package com.zackratos.ultimatebarx.library.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.zackratos.ultimatebarx.library.extension.getNavigationBarHeight
import com.zackratos.ultimatebarx.library.extension.getStatusBarHeight

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 19:45
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class ViewGroupCreator(private val viewGroup: ViewGroup?, tag: Tag): BaseCreator(tag) {

    override fun getStatusBarView(context: Context, fitWindow: Boolean): View {
        var statusBar: View? = viewGroup?.findViewWithTag(tag.statusBarViewTag())
        if (statusBar == null) {
            statusBar = View(context)
            statusBar.tag = tag.statusBarViewTag()
            viewGroup?.addView(statusBar, ViewGroup.LayoutParams.MATCH_PARENT, context.getStatusBarHeight())
        }
        statusBar.post { statusBar.translationY = -statusBar.top.toFloat() }
        // 防止因为 setPadding 导致 navigationBar 的 bottom 变化
        val navigationBar: View? = viewGroup?.findViewWithTag(tag.navigationBarViewTag())
        val height = viewGroup?.height ?: 0
        navigationBar?.post { navigationBar.translationY = (height - navigationBar.bottom).toFloat() }
        return statusBar
    }

    override fun getNavigationBarView(context: Context, fitWindow: Boolean): View {
        var navigationBar: View? = viewGroup?.findViewWithTag(tag.navigationBarViewTag())
        if (navigationBar == null) {
            navigationBar = View(context)
            navigationBar.tag = tag.navigationBarViewTag()
            viewGroup?.addView(navigationBar, ViewGroup.LayoutParams.MATCH_PARENT, context.getNavigationBarHeight())
        }
        val height = viewGroup?.height ?: 0
        navigationBar.post { navigationBar.translationY = (height - navigationBar.bottom).toFloat() }
        return navigationBar
    }
}