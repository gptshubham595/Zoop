package com.zoop.domain.repository

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.category.CategoriesListModel

interface CategoriesRepo {
//    suspend fun getCategories(): ResultWrapper<List<String>>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}