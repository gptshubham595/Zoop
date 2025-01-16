package com.zoop.core.di

import com.zoop.domain.di.useCaseModule
import org.koin.dsl.module

val domainModule = module {
    includes(repositoryModule,useCaseModule)
}