package com.seekasia.jobseeker.di

import androidx.paging.PagingData
import com.seekasia.jobseeker.core.domain.NoParam
import com.seekasia.jobseeker.core.domain.UseCase
import com.seekasia.jobseeker.data.model.JobModel
import com.seekasia.jobseeker.data.repository.AppJobRepository
import com.seekasia.jobseeker.domain.repository.JobRepository
import com.seekasia.jobseeker.domain.use_case.FetchJobsUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module

val domainModule = module {
    factory<JobRepository> { AppJobRepository(apiProvider = get()) }
    factory<UseCase<Flow<PagingData<JobModel>>, NoParam>> { FetchJobsUseCase(repository = get()) }
}
