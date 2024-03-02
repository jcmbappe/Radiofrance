package com.mbappe.repositories.implementation

import com.mbappe.common.ApiResponse
import com.mbappe.datasource.StationDataSource
import com.mbappe.models.Station
import com.mbappe.models.StationAssets
import com.mbappe.network_graph_ql.getAssetsByStationId
import com.mbappe.repositories.StationsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StationsRepositoryImpl @Inject constructor(
    private val stationDataSource: StationDataSource,
    private val coroutineDispatcher: CoroutineDispatcher,
) : StationsRepository {
    override suspend fun getStations(): Flow<ApiResponse<List<Station>>> =
        withContext(coroutineDispatcher) {
            stationDataSource.getBrands()
        }

    override fun getStationAssets(stationId: String): StationAssets = getAssetsByStationId(stationId)
}