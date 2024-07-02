package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference {

    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context) {

        sharedPreference = context.getSharedPreferences("Shered", Context.MODE_PRIVATE)

    }

    var title : String?
        get() = sharedPreference.getString("title"," ")
        set(value) = sharedPreference.edit().putString("title",value)!!.apply()

    var isBoard: Boolean

        get() = sharedPreference.getBoolean("board", false)
        set(value) = sharedPreference.edit().putBoolean("board",value).apply()


    var isLinearLayout: Boolean
        get() = sharedPreference.getBoolean("isLinearLayout", true) // по умолчанию линейный макет
        set(value) = sharedPreference.edit().putBoolean("isLinearLayout", value).apply()





}