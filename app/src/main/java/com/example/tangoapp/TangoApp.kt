package com.example.tangoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TangoApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}