package com.zoop.core.network

import com.zoop.common.APIConstants.CATEGORY_URL
import com.zoop.common.APIConstants.PRODUCTS_URL
import com.zoop.common.ResultWrapper
import com.zoop.common.safeApiCall
import com.zoop.core.transformers.toDomain
import com.zoop.data.models.ProductDTO
import com.zoop.domain.models.Product
import com.zoop.domain.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class NetworkServiceImpl(private val client: HttpClient) : NetworkService {
    override suspend fun getProducts(category: String?): ResultWrapper<List<Product>> {
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
    }
}