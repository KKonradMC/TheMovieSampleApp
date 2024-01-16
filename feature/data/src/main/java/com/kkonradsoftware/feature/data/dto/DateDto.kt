package com.kkonradsoftware.feature.data.dto

import com.google.gson.annotations.SerializedName
import com.kkonradsoftware.common.domain.const.DASH
import com.kkonradsoftware.feature.domain.model.DateModel

data class DateDto(
    @SerializedName("maximum") val maximum: String?,
    @SerializedName("minimum") val minimum: String?,
) {
    fun toDomain() = DateModel(
        minimum = minimum ?: DASH,
        maximum = maximum ?: DASH,
    )
}