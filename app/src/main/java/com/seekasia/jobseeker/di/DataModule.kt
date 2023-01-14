package com.seekasia.jobseeker.di

import com.seekasia.jobseeker.BuildConfig
import com.seekasia.jobseeker.data.data_resource.remote.api_manager.RetrofitApiManager
import com.seekasia.jobseeker.data.data_resource.remote.api_providers.JobApiProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single {
        RetrofitApiManager().provide(androidContext(), BuildConfig.API_BASE_URL)
    }
    factory<JobApiProvider> {
        get<Retrofit>().create(JobApiProvider::class.java)
    }
}
