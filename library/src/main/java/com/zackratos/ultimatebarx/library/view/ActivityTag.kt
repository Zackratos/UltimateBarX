package com.zackratos.ultimatebarx.library.view

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 21:18
 * @email    : 869649338@qq.com
 * @Describe :
 */
class ActivityTag private constructor(): Tag {

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = ActivityTag()
    }

    override fun statusBarViewTag(): String = "activity_status_bar"

    override fun navigationBarViewTag(): String = "activity_navigation_bar"
}