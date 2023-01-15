package com.seekasia.jobseeker.features.job.presentation.main

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.medicus.app.presentation.main.MainViewModel
import com.seekasia.jobseeker.R
import com.seekasia.jobseeker.core.presentation.base.BaseActivity
import com.seekasia.jobseeker.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var navController: NavController

    override fun layoutId(): Int = R.layout.activity_main
    override fun getVM(): MainViewModel = mainViewModel
    override fun bindVM(binding: ActivityMainBinding, vm: MainViewModel) {
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController
        navController.let { navController ->
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}