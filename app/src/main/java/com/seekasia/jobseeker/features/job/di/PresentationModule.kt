package com.seekasia.jobseeker.features.job.di

import com.medicus.app.presentation.main.MainViewModel
import com.seekasia.jobseeker.features.job.presentation.job_details.JobDetailsViewModel
import com.seekasia.jobseeker.features.job.presentation.jobs.JobsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel() }
    viewModel { JobsFragmentViewModel(
        fetchJobsUseCase = get()
    ) }
    viewModel { JobDetailsViewModel() }
}
