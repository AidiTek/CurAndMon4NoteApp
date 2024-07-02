package com.example.noteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController

import androidx.navigation.findNavController

import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.SharedPreference
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences = SharedPreference()
        sharedPreferences.unit(this)

         navController= findNavController(R.id.fragment)



        if (!sharedPreferences.isBoard) {
            navController.navigate(R.id.onBoardFragment)
        }else{
            checkFirebaseAuth()
        }
    }

    private fun checkFirebaseAuth() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user!=null){
            navController.navigate(R.id.noteFragment)
        }else{
            navController.navigate(R.id.singUpFragment)
        }
    }
}