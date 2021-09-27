package com.ricky.color_picker

import android.graphics.Color
import java.util.*

/**
 *
 * @author RickyHal
 * @date 2021/9/26
 */
object Util {
    fun color2Hex(color: Int, alpha: Int = 255, isUpperCase: Boolean = true): String {
        val sb = StringBuffer()
        var R: String = Integer.toHexString(Color.red(color))
        var G: String = Integer.toHexString(Color.green(color))
        var B: String = Integer.toHexString(Color.blue(color))
        var A: String = Integer.toHexString(alpha)
        R = if (R.length == 1) "0$R" else R
        G = if (G.length == 1) "0$G" else G
        B = if (B.length == 1) "0$B" else B
        A = if (A.length == 1) "0$A" else A
        sb.append("#")
        if (isUpperCase) {
            sb.append(A.uppercase(Locale.ROOT))
            sb.append(R.uppercase(Locale.ROOT))
            sb.append(G.uppercase(Locale.ROOT))
            sb.append(B.uppercase(Locale.ROOT))
        } else {
            sb.append(A)
            sb.append(R)
            sb.append(G)
            sb.append(B)
        }
        return sb.toString()
    }

    fun hex2Color(hex: String): Int? {
        return if (isValidHex(hex)) Color.parseColor(hex) else null
    }

    fun isValidHex(color: String): Boolean {
        if (!color.startsWith("#")) return false
        if (color.length != 7 && color.length != 9) return false
        return color.matches(Regex("(#[0-9a-zA-Z]{6})|(#[0-9a-zA-Z]{8})"))
    }
}