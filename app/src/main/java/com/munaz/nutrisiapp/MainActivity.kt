package com.munaz.nutrisiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.munaz.nutrisiapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var backPressedOnce = false
    private val doubleClickInterval = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController=navHostFragment.navController
        navController.navigate(R.id.splasFragment)
        val bottomNavigationView :BottomNavigationView=findViewById(R.id.bottomNav)
        bottomNavigationView.setupWithNavController(navController)

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
    override fun onBackPressed() {
        if (backPressedOnce) {
            onBackPressedDispatcher.onBackPressed()
            return
        }
        this.backPressedOnce = true
        Toast.makeText(this, "Double click back to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({
            backPressedOnce=false
        }, 3000)
    }

}