package com.zackratos.ultimatebarx.ultimatebarx.operator

import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/12/4  8:43 PM
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal class DoubleOperator private constructor(): Operator {

    companion object {
        fun newInstance(): DoubleOperator = DoubleOperator()
    }

    private var staOperator: BaseOperator? = null

    private var navOperator: BaseOperator? = null

    private val newStaConfig by lazy {
        BarConfig.newInstance().apply {
            staOperator?.let { update(it.config) }
        }
    }

    private val newNavConfig by lazy {
        BarConfig.newInstance().apply {
            navOperator?.let { update(it.config) }
        }
    }


    fun staOperator(operator: BaseOperator?): DoubleOperator {
        staOperator = operator
        return this
    }

    fun navOperator(operator: BaseOperator?): DoubleOperator {
        navOperator = operator
        return this
    }

    override fun applyStatusBar() {
        staOperator?.config(newStaConfig)
        staOperator?.applyStatusBar()
    }

    override fun applyNavigationBar() {
        navOperator?.config(newNavConfig)
        navOperator?.applyNavigationBar()
    }

    override fun config(config: BarConfig): Operator {
        newStaConfig.update(config)
        newNavConfig.update(config)
        return this
    }

    override fun transparent(): Operator {
        newStaConfig.transparent()
        newNavConfig.transparent()
        return this
    }

    override fun light(light: Boolean): Operator {
        newStaConfig.light(light)
        newNavConfig.light(light)
        return this
    }

    override fun fitWindow(fitWindow: Boolean): Operator {
        newStaConfig.fitWindow(fitWindow)
        newNavConfig.fitWindow(fitWindow)
        return this
    }

    override fun drawableRes(drawableRes: Int): Operator {
        newStaConfig.drawableRes(drawableRes)
        newNavConfig.drawableRes(drawableRes)
        return this
    }

    override fun colorRes(colorRes: Int): Operator {
        newStaConfig.colorRes(colorRes)
        newNavConfig.colorRes(colorRes)
        return this
    }

    override fun color(color: Int): Operator {
        newStaConfig.color(color)
        newNavConfig.color(color)
        return this
    }

    override fun background(background: BarBackground): Operator {
        newStaConfig.background(background)
        newNavConfig.background(background)
        return this
    }

    override fun lvLightBackground(background: BarBackground): Operator {
        newStaConfig.lvLightBackground(background)
        newNavConfig.lvLightBackground(background)
        return this
    }

    override fun lvLightColor(color: Int): Operator {
        newStaConfig.lvLightColor(color)
        newNavConfig.lvLightColor(color)
        return this
    }

    override fun lvLightColorRes(colorRes: Int): Operator {
        newStaConfig.lvLightColorRes(colorRes)
        newNavConfig.lvLightColorRes(colorRes)
        return this
    }

    override fun lvLightDrawableRes(drawableRes: Int): Operator {
        newStaConfig.lvLightDrawableRes(drawableRes)
        newNavConfig.lvLightDrawableRes(drawableRes)
        return this
    }

}