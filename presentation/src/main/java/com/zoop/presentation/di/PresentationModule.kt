package com.zoop.presentation.di

import org.koin.dsl.module

val presentationModule = module {
    includes(viewModelModule)
}