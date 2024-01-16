package com.kkonradsoftware.feature.domain.usecase

import com.kkonradsoftware.common.domain.UseCase
import com.kkonradsoftware.feature.domain.provider.FavouriteDataProvider
import javax.inject.Inject

class IsFavouriteUseCase @Inject constructor(
    private val favouriteDataProvider: FavouriteDataProvider,
) : UseCase<IsFavouriteUseCase.Params, Boolean> {

    data class Params(
        val id: Int,
    )

    override suspend fun invoke(params: Params): Boolean =
        favouriteDataProvider.isFavourite(id = params.id)


}