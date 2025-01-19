package com.zoop.presentation.navigation

import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.zoop.presentation.models.UiProductModel
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

val productNavType = object : NavType<UiProductModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UiProductModel? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return bundle.getParcelable(key, UiProductModel::class.java)
        }
        return bundle.getParcelable(key) as? UiProductModel
    }

    override fun parseValue(value: String): UiProductModel {
        val item = Json.decodeFromString<UiProductModel>(value)
        return item.copy(
            image = URLDecoder.decode(item.image, "UTF-8"),
            description = String(
                Base64.getDecoder().decode(item.description.replace("_", "/").toByteArray())
            ),
            title = String(
                Base64.getDecoder().decode(item.title.replace("_", "/").toByteArray())
            )
        )
    }

    override fun put(bundle: Bundle, key: String, value: UiProductModel) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: UiProductModel): String {
        val encodedValue = value.copy(
            image = URLEncoder.encode(value.image, "UTF-8"),
            description = String(
                Base64.getEncoder().encode(value.description.toByteArray())
            ).replace("/", "_"),
            title = String(
                Base64.getEncoder().encode(value.title.toByteArray())
            ).replace("/", "_")
        )
        return Json.encodeToString<UiProductModel>(
            value = encodedValue,
            serializer = UiProductModel.serializer()
        )
    }
}