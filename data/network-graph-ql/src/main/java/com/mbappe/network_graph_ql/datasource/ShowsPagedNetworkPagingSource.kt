package com.mbappe.network_graph_ql.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.mbappe.models.Show
import com.mbappe.network_graph_ql.mapToLoadResult
import com.mbappe.network_graph_ql.toShow
import com.mbappe.radiofrance.GetShowsQuery
import com.mbappe.radiofrance.type.StationsEnum
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ShowsPagedNetworkPagingSource @AssistedInject constructor(
    @Assisted private val stationId: String,
    private val client: ApolloClient
) : PagingSource<String, Show>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Show> {
        val stationEnumId = StationsEnum.valueOf(stationId)

        if (stationEnumId == StationsEnum.UNKNOWN__) {
            return LoadResult.Error(Error("Wrong station ID"))
        }

        if (params is LoadParams.Prepend) {
            return LoadResult.Error(Error("No prepend available"))
        }

        val key = if (params is LoadParams.Append) params.key else null

        return try {
            client.query(
                GetShowsQuery(
                    stationId = stationEnumId,
                    showPerPage = Optional.present(params.loadSize),
                    after = Optional.presentIfNotNull(key)
                )
            ).execute().mapToLoadResult(
                getPreviousKey = { data: GetShowsQuery.Data ->
                    data.shows?.edges?.firstOrNull()?.cursor
                },
                getNextKey = { data: GetShowsQuery.Data ->
                    data.shows?.edges?.lastOrNull()?.cursor
                },
                transformation = { data ->
                    data.shows?.edges?.mapNotNull { edge ->
                        edge?.node?.toShow()
                    } ?: listOf()
                }
            )

        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Show>): String? {
        return null
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted stationId: String): ShowsPagedNetworkPagingSource
    }
}