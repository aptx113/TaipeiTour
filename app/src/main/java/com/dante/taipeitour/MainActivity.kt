package com.dante.taipeitour

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.dante.taipeitour.common.attractionKey
import com.dante.taipeitour.common.en
import com.dante.taipeitour.common.es
import com.dante.taipeitour.common.id
import com.dante.taipeitour.common.ja
import com.dante.taipeitour.common.ko
import com.dante.taipeitour.common.nameKey
import com.dante.taipeitour.common.systemChinesePrefix
import com.dante.taipeitour.common.systemEnglishPrefix
import com.dante.taipeitour.common.systemIndonesianTag
import com.dante.taipeitour.common.th
import com.dante.taipeitour.common.vi
import com.dante.taipeitour.common.zhCn
import com.dante.taipeitour.common.zhTw
import com.dante.taipeitour.databinding.ActivityMainBinding
import com.dante.taipeitour.feature.attractions.AttractionsViewModel
import com.dante.taipeitour.model.Attraction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private val sharedViewModel by viewModels<AttractionsViewModel>()

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
        initializeAppLanguage()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        return if (navController.currentDestination?.id == R.id.attractionsFragment) {
            inflater.inflate(R.menu.localization_menu, menu)
            true
        } else {
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val languageTag = when (item.itemId) {
            R.id.zh_tw -> {
                zhTw
            }

            R.id.zh_cn -> {
                zhCn
            }

            R.id.en -> {
                en
            }

            R.id.ja -> {
                ja
            }

            R.id.ko -> {
                ko
            }

            R.id.es -> {
                es
            }

            R.id.id -> {
                id
            }

            R.id.th -> {
                th
            }

            R.id.vi -> {
                vi
            }

            else -> sharedViewModel.selectedLanguage.value
        }
        val appLocale = LocaleListCompat.forLanguageTags(languageTag)
        AppCompatDelegate.setApplicationLocales(appLocale)
        sharedViewModel.onLanguageSelected(languageTag)
        return super.onOptionsItemSelected(item)
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
            invalidateOptionsMenu()
        }
    }

    private fun initializeAppLanguage() {
        val currentLocaleName = this.resources.configuration.locales[0].toLanguageTag()
        val transformedLocalName = when {
            currentLocaleName.contains(systemChinesePrefix, true) -> currentLocaleName.lowercase()
            currentLocaleName.contains(systemEnglishPrefix, true) -> en
            currentLocaleName.contains(systemIndonesianTag, true) -> id
            currentLocaleName == ja || currentLocaleName == ko || currentLocaleName == es || currentLocaleName == th || currentLocaleName == vi -> currentLocaleName
            else -> en
        }
        sharedViewModel.onLanguageSelected(transformedLocalName)
    }
}
