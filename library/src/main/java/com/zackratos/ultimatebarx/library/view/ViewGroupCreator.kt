package com.zackratos.ultimatebarx.library.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
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
        statusBar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                statusBar.translationY = -statusBar.top.toFloat()
                statusBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
        // 防止因为 setPadding 导致 navigationBar 的 bottom 变化
        val navigationBar: View? = viewGroup?.findViewWithTag(tag.navigationBarViewTag())
        navigationBar?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val height = viewGroup?.height ?: 0
                navigationBar.translationY = (height - navigationBar.bottom).toFloat()
                navigationBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
        return statusBar
    }

    override fun getNavigationBarView(context: Context, fitWindow: Boolean): View {
        var navigationBar: View? = viewGroup?.findViewWithTag(tag.navigationBarViewTag())
        if (navigationBar == null) {
            navigationBar = View(context)
            navigationBar.tag = tag.navigationBarViewTag()
            viewGroup?.addView(navigationBar, ViewGroup.LayoutParams.MATCH_PARENT, context.getNavigationBarHeight())
        }
        navigationBar.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val height = viewGroup?.height ?: 0
                navigationBar.translationY = (height - navigationBar.bottom).toFloat()
                navigationBar.viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        })
        return navigationBar
    }
}