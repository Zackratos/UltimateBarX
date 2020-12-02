package com.zackratos.ultimatebarx.library.operator

import android.os.Build
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.library.extension.*

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 18:31
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class FragmentOperator private constructor(val fragment: Fragment): BaseOperator() {

    companion object {
        internal fun newInstance(fragment: Fragment) = FragmentOperator(fragment)
    }

    override fun applyStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        fragment.requireActivity().ultimateBarXInitialization()
        fragment.ultimateBarXInitialization()
        val navLight = manager.getNavigationBarLight(fragment)
        fragment.requireActivity().barLight(config.light, navLight)
        fragment.updateStatusBar(config)
        fragment.requireActivity().defaultNavigationBar()
        fragment.addObserver()
        fragment.requireActivity().addObserver()
    }

    override fun applyNavigationBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return
        fragment.requireActivity().ultimateBarXInitialization()
        fragment.ultimateBarXInitialization()
        val staLight = manager.getStatusBarLight(fragment)
        fragment.requireActivity().barLight(staLight, config.light)
        fragment.updateNavigationBar(config)
        fragment.requireActivity().defaultStatusBar()
        fragment.addObserver()
        fragment.requireActivity().addObserver()
    }

}