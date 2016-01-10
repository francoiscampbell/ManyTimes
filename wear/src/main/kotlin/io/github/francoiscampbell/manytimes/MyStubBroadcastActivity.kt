package io.github.francoiscampbell.manytimes

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Example shell activity which simply broadcasts to our receiver and exits.
 */
class MyStubBroadcastActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val i = Intent()
        i.setAction("io.github.francoiscampbell.manytimes.SHOW_NOTIFICATION")
        i.putExtra(MyPostNotificationReceiver.CONTENT_KEY, getString(R.string.title))
        sendBroadcast(i)
        finish()
    }
}
