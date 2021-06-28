package com.example.tangoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tangoapp.R
import com.example.tangoapp.utils.getNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var exit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNavController().setGraph(R.navigation.main_nav)
    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostFragment)
        if (navHost?.childFragmentManager?.backStackEntryCount == 0) {
            if (exit) {
                finish()
            }
            exit = true

            val snackbar = Snackbar.make(navHost.requireView(), getString(R.string.snackbar_exit), Snackbar.LENGTH_LONG)
            snackbar.show()

            Handler().postDelayed({ exit = false }, 3 * 1000)
        } else {
            super.onBackPressed()
        }
    }
}