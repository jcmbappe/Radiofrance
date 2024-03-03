package com.mbappe.radiofrance.shows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mbappe.models.Show
import com.mbappe.models.StationAssets
import com.mbappe.radiofrance.ui.component.atoms.LoaderAtom
import com.mbappe.radiofrance.ui.component.molecules.ShowHeaderMolecule
import com.mbappe.radiofrance.ui.component.organisms.showCardItems

@Composable
internal fun ShowsRoute(
    modifier: Modifier = Modifier,
    showViewModel: ShowsViewModel = hiltViewModel(),
) {
    val showsPagingItems: LazyPagingItems<Show> =
        showViewModel.pagingShowsFlow.collectAsLazyPagingItems()

    stationsScreen(
        stationAsset = showViewModel.stationAssets,
        showsPagingItems = showsPagingItems,
        modifier = modifier
    )
}

@Composable
private fun stationsScreen(
    stationAsset: StationAssets,
    showsPagingItems: LazyPagingItems<Show>,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            ShowHeaderMolecule(imageRectangleUrl = stationAsset.logoRectangleUrl)
        }
        if (showsPagingItems.loadState.refresh is LoadState.Loading) {
            item {
                LoaderAtom(
                    modifier = Modifier.fillParentMaxWidth(),
                    rawRes = stationAsset.loadingRes
                )
            }
        } else {
            showCardItems(
                modifier = Modifier.padding(horizontal = 15.dp),
                showsPagingItems = showsPagingItems,
                stationColor = Color(stationAsset.colorHex)
            )

            item {
                if (showsPagingItems.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
    }
}

