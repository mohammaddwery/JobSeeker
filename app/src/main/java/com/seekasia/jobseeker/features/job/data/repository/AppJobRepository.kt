package com.seekasia.jobseeker.features.job.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.seekasia.jobseeker.core.AppConstants
import com.seekasia.jobseeker.core.Industries
import com.seekasia.jobseeker.core.JobLocations
import com.seekasia.jobseeker.core.JobStatus
import com.seekasia.jobseeker.core.data.data_resource.remote.exception.ApiException
import com.seekasia.jobseeker.core.data.data_resource.remote.exception.NoConnectionException
import com.seekasia.jobseeker.core.data.data_resource.remote.response.ErrorResponse
import com.seekasia.jobseeker.features.job.data.model.Company
import com.seekasia.jobseeker.features.job.data.model.JobModel
import com.seekasia.jobseeker.features.job.data.model.SalaryRange
import com.seekasia.jobseeker.core.data.paging_source.AppPagingSource
import com.seekasia.jobseeker.features.job.data.data_resource.remote.JobApiProvider
import com.seekasia.jobseeker.features.job.domain.repository.JobRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import java.util.*

class AppJobRepository(
    private val apiProvider: JobApiProvider
): JobRepository {
    override suspend fun fetchJobs(): Flow<PagingData<JobModel>> = Pager(
        config = PagingConfig(pageSize = AppConstants.JOBS_PAGE_SIZE, prefetchDistance = 2),
        pagingSourceFactory = { 
            AppPagingSource(
//                fetchResults = { page, pageSize ->  apiProvider.getJobs(page ,pageSize).data }, TODO: We should use this commented code.
                fetchResults = { page, pageSize -> getMigrationJobs().shuffled() }, // TODO: Remove this when you can use the real API
            )
        }
    ).flow

    /**
     * This Code is ONLY for displaying the results cause I couldn't run the backend services
     * so I mocked the response's body via this method
     **/
    private suspend fun getMigrationJobs(): List<JobModel> {
        delay(2500)
        return when(Random().nextInt(7)) {
            1 -> throw NoConnectionException()
            2 -> throw ApiException(ErrorResponse(statusCode = "500", message= "Something went wrong"))
            3 -> listOf()
            else -> listOf(
                JobModel(
                    id = "job-1",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer, Job Description for a Junior Mobile Developer, Job Description for a Junior Mobile Developer\nJob Description for a Junior Mobile Developer\nJob Description for a Junior Mobile Developer, Job Description for a Junior Mobile Developer. Job Description for a Junior Mobile Developer.",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-2",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-3",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-4",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-5",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-6",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-7",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
                JobModel(
                    id = "job-8",
                    positionTitle = "Junior Mobile Developer",
                    description = "Job Description for a Junior Mobile Developer",
                    company = Company(id = "company-1", name =  "SEEK Ltd."),
                    salaryRange = SalaryRange(min = 1023.0, max = 2031.0),
                    location = JobLocations.Australia,
                    status = JobStatus.PUBLISHED,
                    industry = Industries.Technology,
                    createdAt = Date(),
                ),
            )
        }
    }
}