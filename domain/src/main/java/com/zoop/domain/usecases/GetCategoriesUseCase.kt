package com.zoop.domain.usecases

import com.zoop.common.ResultWrapper
import com.zoop.domain.models.category.CategoriesListModel
import com.zoop.domain.repository.CategoriesRepo

class GetCategoriesUseCase(private val categoriesRepo: CategoriesRepo) {
    //    suspend operator fun invoke(): ResultWrapper<List<String>> {
//        return categoriesRepo.getCategories()
//    }
    suspend operator fun invoke(): ResultWrapper<CategoriesListModel> {
        return categoriesRepo.getCategories()
    }
}