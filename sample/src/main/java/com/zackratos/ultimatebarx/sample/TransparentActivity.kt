package com.zackratos.ultimatebarx.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zackratos.ultimatebarx.library.UltimateBarX

class TransparentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)
        UltimateBarX.create(UltimateBarX.STATUS_BAR).transparent().apply(this)
        UltimateBarX.create(UltimateBarX.NAVIGATION_BAR).transparent().apply(this)
    }


}
