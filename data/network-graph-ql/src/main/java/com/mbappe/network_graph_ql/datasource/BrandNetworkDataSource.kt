package com.mbappe.network_graph_ql.datasource

import com.apollographql.apollo3.ApolloClient
import com.mbappe.common.ApiResponse
import com.mbappe.datasource.BrandDataSource
import com.mbappe.models.Brand
import com.mbappe.network_graph_ql.mapToApiResponse
import com.mbappe.network_graph_ql.toBrand
import com.mbappe.radiofrance.GetBrandsQuery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrandNetworkDataSource @Inject constructor(
    private val client: ApolloClient
) : BrandDataSource {
    override fun getBrands(): Flow<ApiResponse<List<Brand>>> {
        return client.query(GetBrandsQuery()).toFlow()
            .mapToApiResponse { data ->
                data.brands?.mapNotNull { it?.toBrand() } ?: listOf()
            }
    }
}
