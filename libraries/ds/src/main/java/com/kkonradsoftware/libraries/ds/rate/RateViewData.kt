package com.kkonradsoftware.libraries.ds.rate

import com.kkonradsoftware.libraries.ds.R
import kotlin.math.abs

data class RateViewData(
    val value: Double,
) {

    fun getStartRes(index: Int): Int = when {
        abs(value - index) < 0.0000001 || value < index -> R.drawable.baseline_star_outline_24
        value > index && value < index + 1 -> R.drawable.baseline_star_half_24
        else -> R.drawable.baseline_star_24
    }
}
