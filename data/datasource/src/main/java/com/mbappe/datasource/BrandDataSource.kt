package com.mbappe.datasource

import com.mbappe.common.ApiResponse
import com.mbappe.models.Brand
import kotlinx.coroutines.flow.Flow

interface BrandDataSource {

    fun getBrands() : Flow<ApiResponse<List<Brand>>>
}