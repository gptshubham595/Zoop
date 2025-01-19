package com.zoop.core.transformers

import com.zoop.data.models.categoires.CategoriesListResponse
import com.zoop.data.models.categoires.CategoryDTO
import com.zoop.domain.models.category.CategoriesListModel
import com.zoop.domain.models.category.CategoriesModel

fun CategoryDTO.toDomain() = CategoriesModel(
    id = id,
    image = image,
    title = title,
)


fun CategoriesListResponse.toDomain() = CategoriesListModel(
    data = data.map { it.toDomain() },
    msg = msg
)