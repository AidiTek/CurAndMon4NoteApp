package com.example.noteapp

import android.app.Application
import com.example.noteapp.utils.SheredPreference

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        val sheredPreferenc = SheredPreference()
        sheredPreferenc.unit(this)
    }

}