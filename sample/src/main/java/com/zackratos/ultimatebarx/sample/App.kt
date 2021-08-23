package com.zackratos.ultimatebarx.sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @Author   : zackratos
 * @Date     : 2021/8/23 7:58 下午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
class App: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}