package com.zackratos.ultimatebarx.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.sample.viewpager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        UltimateBarX.create(UltimateBarX.STATUS_BAR)
            .fitWindow(true)
            .bgColorRes(R.color.deepSkyBlue)
            .apply(this)
        UltimateBarX.create(UltimateBarX.NAVIGATION_BAR)
            .fitWindow(true)
            .bgColorRes(R.color.deepSkyBlue)
            .apply(this)
        btnTransparent.setOnClickListener { start(TransparentActivity::class.java) }
        btnSwitch.setOnClickListener { start(SwitchActivity::class.java) }
        btnViewPager.setOnClickListener { start(ViewPagerActivity::class.java) }
        btnScroll.setOnClickListener { start(ScrollActivity::class.java) }
    }

    private fun start(clazz: Class<out Activity>) {
        startActivity(Intent(this, clazz))
    }
}
