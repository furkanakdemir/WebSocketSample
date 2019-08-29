package net.furkanakdemir.websocketsample.ui

import android.os.Bundle
import androidx.fragment.app.commit
import dagger.android.support.DaggerAppCompatActivity
import net.furkanakdemir.websocketsample.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container, MessageListFragment())
            }
        }
    }
}
