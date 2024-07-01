package com.example.noteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController

import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.SheredPreference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences = SheredPreference()
        sharedPreferences.unit(this)

        val isOnboardingCompleted = sharedPreferences.isBoard
        val navController = findNavController(R.id.fragment)

        val currentTime = System.currentTimeMillis()
        val lastLounchTime = sharedPreferences.lastLounchTime

        if (lastLounchTime == 0L || lastLounchTime > currentTime) {
            sharedPreferences.clear()
        }

        if (isOnboardingCompleted) {
            navController.navigate(R.id.noteFragment)
        } else {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}