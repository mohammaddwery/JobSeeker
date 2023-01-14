package com.seekasia.jobseeker.domain.repository

import androidx.paging.PagingData
import com.seekasia.jobseeker.data.model.JobModel
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    suspend fun fetchJobs(): Flow<PagingData<JobModel>>
}