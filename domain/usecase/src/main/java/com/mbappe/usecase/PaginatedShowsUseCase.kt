package com.mbappe.usecase

import androidx.paging.PagingData
import com.mbappe.models.Show
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface PaginatedShowsUseCase {

    fun getFlow(stationId: String, scope: CoroutineScope): Flow<PagingData<Show>>
}