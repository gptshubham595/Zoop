package com.zoop.domain.di

import com.zoop.domain.usecases.GetProductsListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductsListUseCase(get()) }
}