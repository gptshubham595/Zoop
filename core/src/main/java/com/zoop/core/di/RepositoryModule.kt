package com.zoop.core.di

import com.zoop.core.repositories.CategoriesRepoImpl
import com.zoop.core.repositories.ProductRepoImpl
import com.zoop.domain.repository.CategoriesRepo
import com.zoop.domain.repository.ProductRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepo> { ProductRepoImpl(get()) }
    single<CategoriesRepo> { CategoriesRepoImpl(get()) }
}