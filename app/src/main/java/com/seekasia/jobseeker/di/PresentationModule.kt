package com.seekasia.jobseeker.di

import com.medicus.app.presentation.main.MainViewModel
import com.seekasia.jobseeker.presentation.screens.jobs.JobsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel() }
    viewModel { JobsFragmentViewModel(
        fetchJobsUseCase = get()
    ) }
}
