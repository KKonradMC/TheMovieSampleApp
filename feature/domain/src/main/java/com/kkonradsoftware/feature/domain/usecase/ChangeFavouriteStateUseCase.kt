package com.kkonradsoftware.feature.domain.usecase

import com.kkonradsoftware.common.domain.UseCase
import com.kkonradsoftware.feature.domain.writer.FavouriteDataWriter
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(
    private val favouriteDataWriter: FavouriteDataWriter,
) : UseCase<ChangeFavouriteStateUseCase.Params, Boolean> {
    data class Params(
        val id: Int,
    )

    override suspend fun invoke(params: Params) =
        favouriteDataWriter.changeFavourite(id = params.id)
}