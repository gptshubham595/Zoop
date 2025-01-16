package com.zoop.ecommercecompose

import android.app.Application
import com.zoop.core.di.dataModule
import com.zoop.core.di.domainModule
import com.zoop.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZoopApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ZoopApplication)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}