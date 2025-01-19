package com.zoop.presentation.models

import android.os.Parcelable
import com.zoop.domain.models.product.ProductModel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UiProductModel(
    val categoryId: Int,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val title: String
) : Parcelable {
    companion object {
        fun ProductModel.fromProductModel(): UiProductModel {
            return UiProductModel(
                categoryId = this.categoryId,
                description = this.description,
                id = this.id,
                image = this.image,
                price = this.price,
                title = this.title
            )
        }
    }
}
