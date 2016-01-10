package io.github.francoiscampbell.manytimes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.bindView

class MainActivity : AppCompatActivity() {
    private val toolbar: Toolbar by bindView(R.id.toolbar)
    private val fab: FloatingActionButton by bindView(R.id.fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            Snackbar.make(it, "New timer", Snackbar.LENGTH_LONG).show()
            val timePickerDialog = TimePickerDialog(this, { timePicker, hour, minute ->
                Toast.makeText(this, "Time set: $hour:$minute", Toast.LENGTH_SHORT).show()
            }, 0, 0, false)
            timePickerDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Pick Date", { dialogInterface, which ->
                val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                    Toast.makeText(this, "Date set: $year-$month-$day ", Toast.LENGTH_SHORT).show()
                }, 2016, 1, 1)
                datePickerDialog.show()
            })
            timePickerDialog.show()
        }
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
}
