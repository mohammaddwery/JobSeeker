package com.seekasia.jobseeker.presentation.screens.main

import com.seekasia.jobseeker.presentation.screens.base.BaseActivity
import com.medicus.app.presentation.main.MainViewModel
import com.seekasia.jobseeker.R
import com.seekasia.jobseeker.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun layoutId(): Int = R.layout.activity_main
    override fun getVM(): MainViewModel = mainViewModel
    override fun bindVM(binding: ActivityMainBinding, vm: MainViewModel) = Unit
}