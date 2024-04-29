package com.manojyadav.assessmentnewsapp.ui.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.manojyadav.assessmentnewsapp.R
import com.manojyadav.assessmentnewsapp.base.BaseActivity
import com.manojyadav.assessmentnewsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    val mainViewModel: MainViewModel by viewModels()

    override fun onViewReady(savedInstanceState: Bundle?) {
        super.onViewReady(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Today's News"
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        savedInstanceState?.let {
            mainViewModel.hideErrorToast()
        }

        loadThemeDefault()
        val sharedPreferences = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.changetheme.setOnClickListener {
            if (binding.changetheme.isChecked){

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.changetheme.isChecked = true
                editor.putBoolean("darkmodeenabled", true)
                editor.apply()

                val intent = this.intent
                this.finish()
                startActivity(intent)

            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                binding.changetheme.isChecked = false
                editor.putBoolean("darkmodeenabled", false)
                editor.apply()

                val intent = this.intent
                this.finish()
                startActivity(intent)

            }

        }
    }

    private fun loadThemeDefault() {
        val sharedPreferencesretrive = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        val darktheme = sharedPreferencesretrive.getBoolean("darkmodeenabled", false)
        if (darktheme){
            binding.changetheme.isChecked = true

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        }
        else{
            binding.changetheme.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }
    }

    override fun setBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private fun setupBottomNavigationBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.feedFragment,
                R.id.bookmarkFragment
            )
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}