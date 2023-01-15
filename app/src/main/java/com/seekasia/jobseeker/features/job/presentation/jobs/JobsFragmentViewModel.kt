package com.seekasia.jobseeker.features.job.presentation.jobs

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.seekasia.jobseeker.core.domain.*
import com.seekasia.jobseeker.core.presentation.base.BaseViewModel
import com.seekasia.jobseeker.features.job.data.model.JobModel
import kotlinx.coroutines.flow.*

class JobsFragmentViewModel(
    private val fetchJobsUseCase: UseCase<Flow<PagingData<JobModel>>, NoParam>,
) : BaseViewModel() {

    private val _jobsFlow = MutableStateFlow<PagingData<JobModel>>(PagingData.empty())
    val jobsStateFlow: StateFlow<PagingData<JobModel>> = _jobsFlow

    init {
        fetchJobs()
    }

    private fun fetchJobs() = launchPagingAsync(
        execute = { fetchJobsUseCase.call(NoParam()).cachedIn(viewModelScope) },
        onSuccess = { _jobsFlow.value = it.value },
        onError = { }
    )

    fun refreshJobs() {
        fetchJobs()
    }
}