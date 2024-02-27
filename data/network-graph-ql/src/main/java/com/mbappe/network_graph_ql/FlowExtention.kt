package com.mbappe.network_graph_ql

import androidx.paging.PagingSource
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.mbappe.common.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <D : Operation.Data, R> Flow<ApolloResponse<D>>.mapToApiResponse(
    transformation: (data: D) -> R
): Flow<ApiResponse<R>> {
    return map { apolloResponse ->
        if (apolloResponse.hasErrors()) {
            val error = apolloResponse.errors?.firstOrNull()
            ApiResponse.Error(error?.message ?: "Unknown error.")
        } else {
            ApiResponse.Success(transformation.invoke(apolloResponse.dataAssertNoErrors))
        }
    }
}

fun <DATA : Operation.Data, KEY : Any, VALUE : Any> ApolloResponse<DATA>.mapToLoadResult(
    getPreviousKey: (data: DATA) -> KEY?,
    getNextKey: (data: DATA) -> KEY?,
    transformation: (data: DATA) -> List<VALUE>
): PagingSource.LoadResult<KEY, VALUE> {
    return if (hasErrors()) {
        val error = errors?.firstOrNull()
        PagingSource.LoadResult.Error(Error(error?.message ?: "Unknown error."))
    } else {
        PagingSource.LoadResult.Page(
            data = transformation.invoke(dataAssertNoErrors),
            prevKey = getPreviousKey(dataAssertNoErrors),
            nextKey = getNextKey(dataAssertNoErrors),
        )
    }
}

