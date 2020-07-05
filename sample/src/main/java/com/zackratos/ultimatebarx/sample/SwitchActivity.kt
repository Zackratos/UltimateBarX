package com.zackratos.ultimatebarx.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.sample.extension.getColorInt
import kotlinx.android.synthetic.main.activity_switch.*

class SwitchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)
        setRootClick()
        setApply()
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
            if (rgColor.checkedRadioButtonId == R.id.rbGradient) {
                UltimateBarX.create(getType())
                    .fitWindow(getFitWindow())
                    .bgRes(R.drawable.bg_gradient)
                    .light(getLight())
                    .apply(this)
            } else {
                UltimateBarX.create(getType())
                    .fitWindow(getFitWindow())
                    .bgColor(getColor())
                    .light(getLight())
                    .apply(this)
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

    private fun getType(): Int = when (rgType.checkedRadioButtonId) {
        R.id.rbStatus -> UltimateBarX.STATUS_BAR
        R.id.rbNavigation -> UltimateBarX.NAVIGATION_BAR
        else -> -1
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

    override fun onBackPressed() {
        if (tvApply.visibility == View.VISIBLE) {
            controlVisibility(View.INVISIBLE)
            return
        }
        super.onBackPressed()
    }


}
