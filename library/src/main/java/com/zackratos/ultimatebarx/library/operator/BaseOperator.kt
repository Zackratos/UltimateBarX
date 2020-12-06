package com.zackratos.ultimatebarx.library.operator

import com.zackratos.ultimatebarx.library.UltimateBarXManager
import com.zackratos.ultimatebarx.library.bean.BarConfig

/**
 * @Author   : Zackratos
 * @Date     : 2020/11/28 17:29
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal abstract class BaseOperator(val config: BarConfig = BarConfig.newInstance()): Operator {


    protected val manager: UltimateBarXManager by lazy { UltimateBarXManager.getInstance() }

//    protected val config: BarConfig by lazy { config }

    override fun config(config: BarConfig): Operator {
        this.config.update(config)
        return this
    }

    override fun transparent(): Operator {
        config.transparent()
        return this
    }

    override fun light(light: Boolean): Operator {
        config.light(light)
        return this
    }

    override fun fitWindow(fitWindow: Boolean): Operator {
        config.fitWindow(fitWindow)
        return this
    }

    override fun drawableRes(drawableRes: Int): Operator {
        config.drawableRes(drawableRes)
        return this
    }

    override fun colorRes(colorRes: Int): Operator {
        config.colorRes(colorRes)
        return this
    }

    override fun color(color: Int): Operator {
        config.color(color)
        return this
    }

}