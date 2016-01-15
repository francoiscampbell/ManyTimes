package io.github.francoiscampbell.manytimes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by francois on 2016-01-14.
 */
class ClockFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragments = arrayOf(
            ClockFragment.newInstance("Choose hours"),
            ClockFragment.newInstance("Choose minutes"),
            ClockFragment.newInstance("Choose seconds"))

    override fun getItem(position: Int): Fragment? {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    public fun getHours() = fragments[0].getTime()
    public fun getMinutes() = fragments[1].getTime()
    public fun getSeconds() = fragments[2].getTime()

    //    public fun getDuration(): Duration {
    //    }
}