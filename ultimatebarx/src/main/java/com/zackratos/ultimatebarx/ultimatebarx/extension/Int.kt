package com.zackratos.ultimatebarx.ultimatebarx.extension

/**
 * @Author   : Zackratos
 * @Date     : 2021/8/21 0:20
 * @email    : 869649338@qq.com
 * @Describe :
 */
internal fun Int.contain(flags: Int): Boolean = this != clearFlags(flags)

private fun Int.addFlags(flags: Int) = setFlags(flags, flags)

private fun Int.clearFlags(flags: Int): Int = setFlags(0, flags)

private fun Int.setFlags(flags: Int, mask: Int): Int {
    return this and mask.inv() or (flags and mask)
}