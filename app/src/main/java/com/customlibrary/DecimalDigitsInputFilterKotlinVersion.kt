package com.customlibrary

import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import java.util.regex.Pattern

/** Kotlin Version**/
/** Function: limit length before Digits before . and after . **/
/** eg: input 5,3 ; the max output would be 9999.99 **/
class DecimalDigitsInputFilterKotlinVersion: InputFilter {
    var mPattern: Pattern? = null

    fun DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) {
        mPattern =
            Pattern.compile("[0-9]{0," + (digitsBeforeZero - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?")
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val match = TextUtils.concat(
            dest.subSequence(0, dstart),
            source.subSequence(start, end),
            dest.subSequence(dend, dest.length)
        )
        val matcher = mPattern!!.matcher(match)
        return if (!matcher.matches()) "" else null
    }
}