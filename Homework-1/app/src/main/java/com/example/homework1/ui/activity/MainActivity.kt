package com.example.homework1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homework1.R
import com.example.homework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            updateToolbar(destination)
        }
    }

    private fun updateToolbar(destination: NavDestination) {
        val title = when (destination.id) {
            R.id.timerFragment -> getString(R.string.timer)
            R.id.projectsFragment -> getString(R.string.project)
            else -> getString(R.string.app_name)
        }

        supportActionBar?.title = title
    }
}