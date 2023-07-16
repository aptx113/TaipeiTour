package com.dante.taipeitour

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.dante.taipeitour.common.attractionKey
import com.dante.taipeitour.common.nameKey
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.localization_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        return when (item.itemId) {
            R.id.zh_tw -> {
                true
            }

            R.id.zh_cn -> {
                true
            }

            R.id.en -> {
                true
            }

            R.id.ja -> {
                true
            }

            R.id.ko -> {
                true
            }

            R.id.es -> {
                true
            }

            R.id.id -> {
                true
            }

            R.id.th -> {
                true
            }

            R.id.vi -> {
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @Suppress("DEPRECATION")
    private fun setOnDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, bundle ->
            when (destination.id) {
                R.id.attractionDetailsFragment -> {
                    val attraction = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        bundle?.getSerializable(attractionKey, Attraction::class.java)
                    } else {
                        bundle?.getSerializable(attractionKey) as? Attraction
                    }
                    attraction?.let {
                        supportActionBar?.title = it.name
                    }
                }

                R.id.attractionOfficialSiteFragment -> {
                    val name = bundle?.getString(nameKey)
                    supportActionBar?.title = name
                }

                else -> {
                    supportActionBar?.title = getString(R.string.app_name)
                }
            }
        }
    }
}
