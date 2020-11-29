package com.zackratos.ultimatebarx.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zackratos.ultimatebarx.library.annotation.Type
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.library.operator.Operator

class UltimateBarX {
    companion object {

        /**
         * use [com.zackratos.ultimatebarx.library.operator.Operator.applyStatusBar]
         */
        @Deprecated("")
        const val STATUS_BAR = 0

        /**
         * use [com.zackratos.ultimatebarx.library.operator.Operator.applyNavigationBar]
         */
        @Deprecated("")
        const val NAVIGATION_BAR = 1

        /**
         * use [with]
         * and [com.zackratos.ultimatebarx.library.operator.Operator.applyStatusBar]
         * and [com.zackratos.ultimatebarx.library.operator.Operator.applyNavigationBar]
         */
        @Deprecated("")
        @JvmStatic
        fun create(@Type type: Int): BarConfig.Builder = BarConfig.Builder.newDefaultBuilder(type)

        @JvmStatic
        fun with(activity: FragmentActivity): Operator = Operator.get(activity)

        @JvmStatic
        fun with(fragment: Fragment): Operator = Operator.get(fragment)
    }

}