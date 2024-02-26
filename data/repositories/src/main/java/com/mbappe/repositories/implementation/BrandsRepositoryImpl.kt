package com.mbappe.repositories.implementation

import com.mbappe.common.ApiResponse
import com.mbappe.datasource.BrandDataSource
import com.mbappe.models.Brand
import com.mbappe.repositories.BrandsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BrandsRepositoryImpl @Inject constructor(
    private val brandDataSource: BrandDataSource,
    private val coroutineDispatcher: CoroutineDispatcher,
) : BrandsRepository {
    override suspend fun getBrands(): Flow<ApiResponse<List<Brand>>> =
        withContext(coroutineDispatcher) {
            brandDataSource.getBrands()
        }
}