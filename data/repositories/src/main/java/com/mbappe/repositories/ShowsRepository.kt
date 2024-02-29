package com.mbappe.repositories

import androidx.paging.PagingData
import com.mbappe.models.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {

     fun getShows(stationId: String, pageSize: Int): Flow<PagingData<Show>>
}