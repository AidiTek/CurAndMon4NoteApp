package com.example.noteapp

import android.app.Application
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.room.Room
import com.example.noteapp.data.db.AppDataBase
import com.example.noteapp.utils.SharedPreference

class App : Application() {

    private lateinit var navControler: NavController

    private lateinit var conf: AppBarConfiguration

    companion object {

        var appDataBase: AppDataBase? = null

    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferenc = SharedPreference()
        sharedPreferenc.unit(this)

        getInstenc()


    }

     fun getInstenc(): AppDataBase? {

        if (appDataBase == null) {
            appDataBase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigrationFrom().allowMainThreadQueries().build()

            }
        }
        return appDataBase
    }

}