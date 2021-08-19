package com.zackratos.ultimatebarx.ultimatebarx.view

import com.zackratos.ultimatebarx.ultimatebarx.BuildConfig

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 21:18
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class FragmentTag private constructor(): Tag {

    companion object {
        val instance: FragmentTag
            get() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = FragmentTag()
    }

    override val statusBarViewTag: String = "${BuildConfig.LIBRARY_PACKAGE_NAME}_fragment_status_bar"

    override val navigationBarViewTag: String = "${BuildConfig.LIBRARY_PACKAGE_NAME}_fragment_navigation_bar"
}