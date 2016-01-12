package io.github.francoiscampbell.manytimes.shape

/**
 * Created by francois on 2016-01-12.
 */
class Oval(private var a: Double, private var b: Double) {
    private var ab = a * b

    fun getRadiusAtAngle(angle: Double): Double {
        val sin = Math.sin(angle)
        val cos = Math.cos(angle)
        return ab / Math.sqrt(b * b * cos * cos + a * a * sin * sin)
    }
}