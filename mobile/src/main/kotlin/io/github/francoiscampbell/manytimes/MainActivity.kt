package io.github.francoiscampbell.manytimes

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.bindView

class MainActivity : AppCompatActivity(), GetTimeFragment.OnFragmentInteractionListener {
    private val toolbar by bindView<Toolbar>(R.id.toolbar)
    private val fab by bindView<FloatingActionButton>(R.id.fab)
    private val viewPager by bindView<ViewPager>(R.id.frag_placeholder)

    private var oldFragmentIndex = 0
    private val times = arrayOfNulls<Int>(3)
    private val pagerAdapter = MainActivityPagerAdapter(supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Snackbar.make(it, "New timer", Snackbar.LENGTH_LONG).show()
        }

        viewPager.adapter = pagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getTime() {
        val hours = pagerAdapter.getHours()
        val minutes = pagerAdapter.getMinutes()
        val seconds = pagerAdapter.getSeconds()
        Snackbar.make(fab, "The time set is $hours:$minutes:$seconds", Snackbar.LENGTH_LONG).show()
    }
}
