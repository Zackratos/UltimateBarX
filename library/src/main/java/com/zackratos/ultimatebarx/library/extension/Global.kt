package com.zackratos.ultimatebarx.library.extension

import android.content.ContentValues.TAG
import android.text.TextUtils
import android.util.Log
import com.zackratos.ultimatebarx.library.rom.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @Author   : zhangwenchao
 * @Date     : 2020/11/24  8:00 PM
 * @email    : zhangwenchao@soulapp.cn
 * @Describe :
 */

internal fun getRom(): Rom {
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_MIUI)))
        return MiuiRom()
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_EMUI)))
        return EmuiRom()
    if (!TextUtils.isEmpty(getProp(Rom.KEY_VERSION_VIVO)))
        return FuntouchRom()
    return OtherRom()
}

private fun getProp(name: String): String? {
    val line: String?
    var input: BufferedReader? = null
    try {
        val p = Runtime.getRuntime().exec("getprop $name")
        input = BufferedReader(InputStreamReader(p.inputStream), 1024)
        line = input.readLine()
        input.close()
    } catch (ex: IOException) {
        Log.e(TAG, "Unable to read prop $name", ex)
        return null
    } finally {
        if (input != null) {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return line
}