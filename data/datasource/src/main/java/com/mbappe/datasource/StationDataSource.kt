package com.mbappe.datasource

import com.mbappe.common.ApiResponse
import com.mbappe.models.Station
import kotlinx.coroutines.flow.Flow

interface StationDataSource {

    suspend fun getBrands() : Flow<ApiResponse<List<Station>>>
}