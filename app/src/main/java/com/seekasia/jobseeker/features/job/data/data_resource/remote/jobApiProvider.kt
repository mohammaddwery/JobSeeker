package com.seekasia.jobseeker.features.job.data.data_resource.remote

import com.seekasia.jobseeker.core.AppConstants
import com.seekasia.jobseeker.core.data.data_resource.remote.response.PagingResponse
import com.seekasia.jobseeker.features.job.data.model.JobModel
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApiProvider {
    @GET(jobs)
    suspend fun getJobs(
        @Query("page") page : Int = 1,
        @Query("per_page") pageSize : Int = AppConstants.JOBS_PAGE_SIZE,
    ) : PagingResponse<JobModel>
}

const val jobs: String = "jobs/published"

