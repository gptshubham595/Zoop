package com.zoop.core.network

import com.zoop.common.APIConstants.CATEGORIES_URL
import com.zoop.common.APIConstants.CATEGORY_URL
import com.zoop.common.APIConstants.PRODUCTS_URL
import com.zoop.common.ResultWrapper
import com.zoop.common.safeApiCall
import com.zoop.core.transformers.toDomain
import com.zoop.data.models.categoires.CategoriesListResponse
import com.zoop.data.models.product.ProductListResponse
import com.zoop.domain.models.category.CategoriesListModel
import com.zoop.domain.models.product.ProductList
import com.zoop.domain.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class NetworkServiceImpl(private val client: HttpClient) : NetworkService {
    /*override suspend fun getProducts(category: String?): ResultWrapper<List<Product>> {
        val preparedURL = if (category == null)
            PRODUCTS_URL else
            "$CATEGORY_URL/$category"
        return client.safeApiCall<List<ProductDTO>, List<Product>>(
            url = preparedURL,
            method = HttpMethod.Get,
            mapper = { dataModel: List<ProductDTO> ->
                dataModel.map { it.toDomain() }
            }
        )
    }*/
    override suspend fun getProducts(category: Int?): ResultWrapper<ProductList> {
        val preparedURL = if (category == null)
            PRODUCTS_URL else
            "$CATEGORY_URL/$category"
        return client.safeApiCall<ProductListResponse, ProductList>(
            url = preparedURL,
            method = HttpMethod.Get,
            mapper = { dataModel: ProductListResponse ->
                dataModel.toDomain()
            }
        )
    }

    //    override suspend fun getCategories(): ResultWrapper<List<String>> {
//        return client.safeApiCall<List<String>, List<String>>(
//            url = CATEGORIES_URL,
//            method = HttpMethod.Get,
//            mapper = { it }
//        )
//    }
    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        return client.safeApiCall<CategoriesListResponse, CategoriesListModel>(
            url = CATEGORIES_URL,
            method = HttpMethod.Get,
            mapper = { it.toDomain() }
        )
    }
}