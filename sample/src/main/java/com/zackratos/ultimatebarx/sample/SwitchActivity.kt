package com.zackratos.ultimatebarx.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import com.zackratos.ultimatebarx.sample.extension.getResColor
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import kotlinx.android.synthetic.main.activity_switch.*

class SwitchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)
        setRootClick()
        setApply()
        tvApply.performClick()
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

    private fun setApply() {
        tvApply.setOnClickListener {
            when (rgType.checkedRadioButtonId) {
                R.id.rbStatus -> statusBar(::setConfig)
                R.id.rbNavigation -> navigationBar(::setConfig)
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

    private fun setConfig(config: BarConfig) {
        config.run {
            fitWindow = getFitWindow()
            light = getLight()
            background = getBackground()
        }
    }

    private fun getBackground(): BarBackground =
        BarBackground.newInstance().apply {
            when (rgColor.checkedRadioButtonId) {
                R.id.rbGradient -> drawableRes = R.drawable.bg_gradient
                else -> color = this@SwitchActivity.getColor()
            }
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
        R.id.rbCyan -> getResColor(R.color.cyan)
        R.id.rbTransparent -> Color.TRANSPARENT
        R.id.rbAlphaBlack -> getResColor(R.color.alphaBlack)
        R.id.rbAlphaGreen -> getResColor(R.color.alphaGreen)
        else ->Color.TRANSPARENT
    }

    override fun onBackPressed() {
        if (tvApply.visibility == View.VISIBLE) {
            controlVisibility(View.INVISIBLE)
            return
        }
        super.onBackPressed()
    }


}
