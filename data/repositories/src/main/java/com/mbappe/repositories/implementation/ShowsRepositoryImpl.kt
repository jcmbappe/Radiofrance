package com.mbappe.repositories.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mbappe.models.Show
import com.mbappe.network_graph_ql.datasource.ShowsPagedNetworkPagingSource
import com.mbappe.repositories.ShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowsRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: ShowsPagedNetworkPagingSource.Factory,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ShowsRepository {

    override suspend fun getShows(stationId: String, pageSize: Int): Flow<PagingData<Show>> =
        withContext(coroutineDispatcher) {
            Pager(PagingConfig(pageSize)) {
                pagingSourceFactory.create(stationId)
            }.flow
        }
}
