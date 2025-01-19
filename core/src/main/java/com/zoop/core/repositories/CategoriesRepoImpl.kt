package com.zoop.core.repositories

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.category.CategoriesListModel
import com.zoop.domain.network.NetworkService
import com.zoop.domain.repository.CategoriesRepo

class CategoriesRepoImpl(private val networkService: NetworkService) : CategoriesRepo {
//    override suspend fun getCategories(): ResultWrapper<List<String>> {
//        return networkService.getCategories()
//    }
    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        return networkService.getCategories()
    }
}