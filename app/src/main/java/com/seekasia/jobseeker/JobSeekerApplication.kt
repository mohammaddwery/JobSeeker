package com.seekasia.jobseeker

import android.app.Application
import com.seekasia.jobseeker.di.dataModule
import com.seekasia.jobseeker.di.domainModule
import com.seekasia.jobseeker.di.presentationModule
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
            modules(dataModule, domainModule, presentationModule)
        }
    }
}