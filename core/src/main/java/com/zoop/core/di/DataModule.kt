package com.zoop.core.di

import org.koin.dsl.module

val dataModule = module {
    includes(networkModule)
}