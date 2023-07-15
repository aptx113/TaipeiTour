package com.dante.taipeitour

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.dante.taipeitour.databinding.ActivityMainBinding
import com.dante.taipeitour.model.Attraction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        setOnDestinationChangedListener()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    @Suppress("DEPRECATION")
    private fun setOnDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, bundle ->
            when (destination.id) {
                R.id.attractionDetailsFragment -> {
                    val name = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        bundle?.getSerializable("attraction", Attraction::class.java)
                    } else {
                        bundle?.getSerializable("attraction") as? Attraction
                    }
                    name?.let {
                        supportActionBar?.title = it.name
                    }
                }

                else -> {
                    supportActionBar?.title = getString(R.string.app_name)
                }
            }
        }
    }
}
