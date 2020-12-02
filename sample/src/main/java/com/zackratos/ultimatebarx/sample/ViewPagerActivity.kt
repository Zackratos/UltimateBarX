package com.zackratos.ultimatebarx.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.zackratos.ultimatebarx.library.UltimateBarX
import com.zackratos.ultimatebarx.sample.viewpager.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        viewPager.adapter =
            ViewPagerAdapter(
                supportFragmentManager
            )
        initViewPager()
        setTabSelect(0)
        flAndroid.setOnClickListener { viewPager.currentItem = 0 }
        flAlbum.setOnClickListener { viewPager.currentItem = 1 }
        flCamera.setOnClickListener { viewPager.currentItem = 2 }
        flGames.setOnClickListener { viewPager.currentItem = 3 }
        UltimateBarX.with(this)
            .fitWindow(true)
            .colorRes(R.color.deepSkyBlue)
            .light(false)
            .applyNavigationBar()

    }

    private fun initViewPager() {
        viewPager.offscreenPageLimit = 4
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                setTabSelect(position)
            }
        })
    }

    private fun setTabSelect(index: Int) {
        ivAndroid.setImageResource(R.drawable.ic_android_sliver_24dp)
        ivAlbum.setImageResource(R.drawable.ic_album_sliver_24dp)
        ivCamera.setImageResource(R.drawable.ic_camera_sliver_24dp)
        ivGames.setImageResource(R.drawable.ic_games_sliver_24dp)
        when (index) {
            0 -> ivAndroid.setImageResource(R.drawable.ic_android_deep_sky_blue_24dp)
            1 -> ivAlbum.setImageResource(R.drawable.ic_album_deep_sky_blue_24dp)
            2 -> ivCamera.setImageResource(R.drawable.ic_camera_deep_sky_blue_24dp)
            3 -> ivGames.setImageResource(R.drawable.ic_games_deep_sky_blue_24dp)
        }
    }

}
