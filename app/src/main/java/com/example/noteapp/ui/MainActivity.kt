package com.example.noteapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.SharedPreference
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navController = findNavController(R.id.fragment)


        drawerLayout = binding.drawerLayout


        binding.navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                R.id.noteFragment -> {
                    navController.navigate(R.id.noteFragment)
                    true
                }
                R.id.chatFragment -> {
                    navController.navigate(R.id.chatFragment)
                    true
                }
                else -> false
            }
        }


        val sharedPreferences = SharedPreference()
        sharedPreferences.unit(this)

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

    fun openDrawer(){
        drawerLayout.openDrawer(GravityCompat.START)
    }

}