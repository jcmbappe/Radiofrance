package com.mbappe.usecase.interactor

import androidx.paging.cachedIn
import com.mbappe.repositories.ShowsRepository
import com.mbappe.usecase.PaginatedShowsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus
import javax.inject.Inject

class PaginatedShowsInteractor @Inject constructor(
    private val showsRepository: ShowsRepository,
) : PaginatedShowsUseCase {

    override fun getFlow(stationId: String, scope: CoroutineScope) = showsRepository
        .getShows(stationId = stationId, SHOWS_PAGE_SIZE)
        .cachedIn(scope)

    companion object {
        private const val SHOWS_PAGE_SIZE = 10
    }
}