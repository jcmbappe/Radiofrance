package com.mbappe.repositories

import com.mbappe.common.ApiResponse
import com.mbappe.models.Station
import kotlinx.coroutines.flow.Flow

interface StationsRepository {

    suspend fun getStations() : Flow<ApiResponse<List<Station>>>
}