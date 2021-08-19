package com.zackratos.ultimatebarx.ultimatebarx.view

import com.zackratos.ultimatebarx.ultimatebarx.BuildConfig

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 21:18
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class ActivityTag private constructor(): Tag {

    companion object {
        val instance: ActivityTag
            get() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = ActivityTag()
    }

    override val statusBarViewTag: String = "${BuildConfig.LIBRARY_PACKAGE_NAME}_activity_status_bar"

    override val navigationBarViewTag: String = "${BuildConfig.LIBRARY_PACKAGE_NAME}_activity_navigation_bar"
}