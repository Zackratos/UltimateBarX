package com.zackratos.ultimatebarx.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX

class TransparentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transparent)
        UltimateBarX.with(this)
            .transparent()
            .apply {
                applyStatusBar()
                applyNavigationBar()
            }
    }


}
