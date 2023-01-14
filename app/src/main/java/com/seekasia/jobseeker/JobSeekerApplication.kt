package com.seekasia.jobseeker

import android.app.Application
import com.seekasia.jobseeker.di.apiServicesModule
import com.seekasia.jobseeker.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class JobSeekerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@JobSeekerApplication)
            modules(viewModelsModule, apiServicesModule)
        }
    }
}