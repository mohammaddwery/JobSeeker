package com.seekasia.jobseeker.features.job.domain.repository

import androidx.paging.PagingData
import com.seekasia.jobseeker.features.job.data.model.JobModel
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    suspend fun fetchJobs(): Flow<PagingData<JobModel>>
}