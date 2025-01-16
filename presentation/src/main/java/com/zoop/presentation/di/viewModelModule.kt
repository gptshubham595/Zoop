package com.zoop.presentation.di

import com.zoop.presentation.ui.feature.home.HomeViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}