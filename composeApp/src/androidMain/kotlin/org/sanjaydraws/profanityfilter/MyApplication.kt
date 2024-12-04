package org.sanjaydraws.profanityfilter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.sanjaydraws.profanityfilter.di.configureKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin {
            androidContext(this@MyApplication)
        }
    }
}