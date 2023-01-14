package com.seekasia.jobseeker.presentation.screens.jobs

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.seekasia.jobseeker.core.domain.*
import com.seekasia.jobseeker.presentation.screens.base.BaseViewModel
import com.seekasia.jobseeker.data.model.JobModel
import kotlinx.coroutines.flow.*

class JobsFragmentViewModel(
    private val fetchJobsUseCase: UseCase<Flow<PagingData<JobModel>>, NoParam>,
) : BaseViewModel() {
    private lateinit var _jobsFlow: Flow<PagingData<JobModel>>
    val jobsFlow: Flow<PagingData<JobModel>>
        get() = _jobsFlow

    init {
        fetchJobs()
    }

    private fun fetchJobs() = launchPagingAsync(
        execute = { fetchJobsUseCase.call(NoParam()).cachedIn(viewModelScope) },
        onSuccess = { _jobsFlow = it },
        onError = { }
    )
}