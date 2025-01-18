package com.zoop.presentation.di

import coil3.ImageLoader
import coil3.network.NetworkClient
import coil3.network.NetworkFetcher
import coil3.util.DebugLogger
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)

    single {
        NetworkFetcher.Factory(
            networkClient = { get<NetworkClient>() }
        )
    }

    single {
        ImageLoader.Builder(androidContext())
            .components {
                add(
                    factory = get<NetworkFetcher.Factory>()
                )
            }
            .logger(DebugLogger())
            .build()
    }
}