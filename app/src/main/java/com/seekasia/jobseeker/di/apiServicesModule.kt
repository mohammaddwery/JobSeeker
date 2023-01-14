package com.seekasia.jobseeker.di

import com.seekasia.jobseeker.BuildConfig
import com.seekasia.jobseeker.data.data_resource.remote.api_manager.RetrofitApiManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val apiServicesModule = module {
    single {
        RetrofitApiManager().provide(androidContext(), BuildConfig.API_BASE_URL)
    }
}
