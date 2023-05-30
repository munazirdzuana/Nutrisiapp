package com.munaz.nutrisiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.munaz.nutrisiapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController=navHostFragment.navController
        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottomNav)
        setupWithNavController(bottomNavigationView, navController  )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.rekomenFragment -> showBottomNav()
                R.id.favoriteFragment -> showBottomNav()
                R.id.akunFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.bottomNav.visibility = View.VISIBLE
    }
}