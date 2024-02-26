package com.mbappe.repositories

import com.mbappe.common.ApiResponse
import com.mbappe.models.Brand
import kotlinx.coroutines.flow.Flow

interface BrandsRepository {

    suspend fun getBrands() : Flow<ApiResponse<List<Brand>>>
}