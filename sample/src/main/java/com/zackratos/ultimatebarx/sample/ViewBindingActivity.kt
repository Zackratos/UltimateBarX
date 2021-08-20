package com.zackratos.ultimatebarx.sample

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @Author   : zackratos
 * @Date     : 2021/8/20 10:41 上午
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */
open class ViewBindingActivity<out VB: ViewBinding>: AppCompatActivity() {

    protected val binding: VB by lazy {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        method.invoke(null, layoutInflater) as VB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}