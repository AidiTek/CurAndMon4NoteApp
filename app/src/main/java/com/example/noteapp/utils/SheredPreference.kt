package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class SheredPreference {

    private lateinit var sheredPreference: SharedPreferences

    fun unit(context: Context) {

        sheredPreference = context.getSharedPreferences("Shered", Context.MODE_PRIVATE)

    }

    var title : String?
        get() = sheredPreference.getString("title"," ")
        set(value) = sheredPreference.edit().putString("title",value)!!.apply()

    var isBoard: Boolean

        get() = sheredPreference.getBoolean("board", false)
        set(value) = sheredPreference.edit().putBoolean("board",value).apply()

    var lastLounchTime: Long

        get() = sheredPreference.getLong("lastLounchTime",0L)
        set(value) = sheredPreference.edit().putLong("lastLounchTime",value).apply()

    fun clear(){
        sheredPreference.edit().clear().apply()
    }

}