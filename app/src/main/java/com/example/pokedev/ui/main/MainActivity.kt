package com.example.pokedev.ui.main

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokedev.R
import com.example.pokedev.common.BaseActivity
import com.example.pokedev.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_profile
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration) //TOOLBAR
        navView.setupWithNavController(navController)
    }

    fun showLoading(show:Boolean){
        if(show){
            binding.flMainLoading.visibility = View.VISIBLE
        }else{
            binding.flMainLoading.visibility = View.GONE
        }
    }
}