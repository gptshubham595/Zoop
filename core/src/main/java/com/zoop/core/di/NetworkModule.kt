package com.zoop.core.di

import android.util.Log
import coil3.network.NetworkClient
import coil3.network.okhttp.asNetworkClient
import com.zoop.core.network.NetworkServiceImpl
import com.zoop.domain.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KtorLog","Log: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
    single<NetworkService> {
        NetworkServiceImpl(get())
    }

    single {
        OkHttpClient.Builder()
            .build()
    }

    single<NetworkClient> {
        get<OkHttpClient>().asNetworkClient()
    }
}