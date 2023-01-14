package com.seekasia.jobseeker.domain.use_case

import androidx.paging.PagingData
import com.seekasia.jobseeker.core.domain.NoParam
import com.seekasia.jobseeker.core.domain.UseCase
import com.seekasia.jobseeker.data.model.JobModel
import com.seekasia.jobseeker.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow

class FetchJobsUseCase(
    private val repository: JobRepository,
): UseCase<Flow<PagingData<JobModel>>, NoParam> {

    override suspend fun call(param: NoParam): Flow<PagingData<JobModel>> =
        repository.fetchJobs()
}