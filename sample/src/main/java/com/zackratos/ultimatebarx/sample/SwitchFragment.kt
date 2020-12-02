package com.zackratos.ultimatebarx.sample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.library.bean.BarConfig
import com.zackratos.ultimatebarx.sample.extension.getColorInt
import kotlinx.android.synthetic.main.activity_switch.*

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/7/8  10:22 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class SwitchFragment: Fragment(R.layout.activity_switch) {

    companion object {
        fun newInstance() = SwitchFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.setImageResource(R.drawable.yurisa_03)
        setRootClick()
        setApply()
    }

    private fun setApply() {
        tvApply.setOnClickListener {
            val config = when (rgColor.checkedRadioButtonId) {
                R.id.rbGradient ->
                    BarConfig.newInstance()
                        .fitWindow(getFitWindow())
                        .drawableRes(R.drawable.bg_gradient)
                        .light(getLight())
                else ->
                    BarConfig.newInstance()
                        .fitWindow(getFitWindow())
                        .color(getColor())
                        .light(getLight())
            }
            val operator = UltimateBarX.with(this).config(config)
            when (rgType.checkedRadioButtonId) {
                R.id.rbStatus -> operator.applyStatusBar()
                R.id.rbNavigation -> operator.applyNavigationBar()
            }
        }
    }

    private var time = 0L
    private var lastTime = 0L

    private fun setRootClick() {
        rlRoot.setOnClickListener {
            lastTime = time
            time = System.currentTimeMillis()
            if (time - lastTime < 300) {
                controlVisibility(View.VISIBLE)
                tvTips.visibility = View.GONE
                lastTime = 0L
                time = 0L
            }
        }
    }

    private fun controlVisibility(visibility: Int) {
        rgType.visibility = visibility
        rgFitWindow.visibility = visibility
        rgLight.visibility = visibility
        rgColor.visibility = visibility
        tvApply.visibility = visibility
    }

    private fun getLight(): Boolean = when (rgLight.checkedRadioButtonId) {
        R.id.rbLight -> true
        R.id.rbNoLight -> false
        else -> false
    }

    private fun getFitWindow(): Boolean = when (rgFitWindow.checkedRadioButtonId) {
        R.id.rbFitWindow -> true
        R.id.rbNoFitWindow -> false
        else -> false
    }

    @ColorInt
    private fun getColor(): Int = when (rgColor.checkedRadioButtonId) {
        R.id.rbRed -> Color.RED
        R.id.rbCyan -> getColorInt(R.color.cyan)
        R.id.rbTransparent -> Color.TRANSPARENT
        R.id.rbAlphaBlack -> getColorInt(R.color.alphaBlack)
        R.id.rbAlphaGreen -> getColorInt(R.color.alphaGreen)
        else ->Color.TRANSPARENT
    }

    fun onBackPressed(): Boolean {
        if (tvApply.visibility == View.VISIBLE) {
            controlVisibility(View.INVISIBLE)
            return false
        }
        return true
    }
}