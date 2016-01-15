package io.github.francoiscampbell.manytimes

/**
 * Created by francois on 2016-01-15.
 */
public fun CharSequence.isNumeric(): Boolean {
    try {
        toString().toInt()
        return true
    } catch (e: NumberFormatException) {
        return false
    }
}
