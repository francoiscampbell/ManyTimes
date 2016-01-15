package io.github.francoiscampbell.manytimes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by francois on 2016-01-14.
 */
class MainActivityPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val fragmentInits = arrayOf(
            { ClockFragment.newInstance("Choose hours") },
            { ClockFragment.newInstance("Choose minutes") },
            { ClockFragment.newInstance("Choose seconds") },
            { GetTimeFragment.newInstance() })

    private val fragments = arrayOfNulls<Fragment>(fragmentInits.size)

    override fun getItem(position: Int): Fragment? {
        val fragment = fragmentInits[position]()
        fragments[position] = fragment
        return fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

    public fun getHours() = (fragments[0] as ClockFragment).getTime()
    public fun getMinutes() = (fragments[1] as ClockFragment).getTime()
    public fun getSeconds() = (fragments[2] as ClockFragment).getTime()

    //    public fun getDuration(): Duration {
    //    }
}