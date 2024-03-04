package com.mbappe.network_graph_ql.datasource

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.mbappe.common.ApiResponse
import com.mbappe.datasource.StationDataSource
import com.mbappe.models.Station
import com.mbappe.network_graph_ql.connectivity.ConnectivityObserver
import com.mbappe.network_graph_ql.mapToApiResponse
import com.mbappe.network_graph_ql.toBrand
import com.mbappe.radiofrance.GetBrandsQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StationNetworkDataSource @Inject constructor(
    private val client: ApolloClient,
    private val connectivityObserver: ConnectivityObserver,
    private val coroutineDispatcher: CoroutineDispatcher
) : StationDataSource {
    override suspend fun getBrands(): Flow<ApiResponse<List<Station>>> {

        return try {
            withContext(coroutineDispatcher) {
                // Apollo crash if no internet
                connectivityObserver.observe().flatMapLatest { status ->
                    when (status) {
                        ConnectivityObserver.Status.Available -> {
                            client.query(GetBrandsQuery())
                                .toFlow()
                                .mapToApiResponse { data ->
                                    data.brands?.mapNotNull { it?.toBrand() } ?: listOf()
                                }
                        }
                        ConnectivityObserver.Status.Unavailable ->
                            flowOf(ApiResponse.Error(errorMessage = "Network Error"))
                        else -> {
                            flow{}
                        }
                    }
                }
            }
        } catch (e: ApolloException) {
            flow {
                emit(ApiResponse.Error(errorMessage = e.message ?: "Apollo Error"))
            }
        } catch (e: Error) {
            flow {
                emit(ApiResponse.Error(errorMessage = e.message ?: "Error"))
            }
        }
    }
}
