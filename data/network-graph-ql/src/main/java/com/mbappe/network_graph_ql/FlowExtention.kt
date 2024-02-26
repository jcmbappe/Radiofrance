package com.mbappe.network_graph_ql

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