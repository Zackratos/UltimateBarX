package com.zackratos.ultimatebarx.sample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zackratos.ultimatebarx.library.UltimateBarX
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
        btnSwitch2.setOnClickListener { start(SwitchFragmentActivity::class.java) }
        btnViewPager.setOnClickListener { start(ViewPagerActivity::class.java) }
        btnViewPager2.setOnClickListener { start(ViewPagerActivity2::class.java) }
        btnScroll.setOnClickListener { start(ScrollActivity::class.java) }
        btnDrawer.setOnClickListener { start(DrawerActivity::class.java) }
    }

    private fun start(clazz: Class<out Activity>) {
        startActivity(Intent(this, clazz))
    }
}
