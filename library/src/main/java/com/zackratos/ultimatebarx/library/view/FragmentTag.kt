package com.zackratos.ultimatebarx.library.view

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 21:18
 * @email    : 869649338@qq.com
 * @Describe :
 */
class FragmentTag private constructor(): Tag {

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = FragmentTag()
    }

    override fun statusBarViewTag(): String = "fragment_status_bar"

    override fun navigationBarViewTag(): String = "fragment_navigation_bar"
}