package com.mbappe.radiofrance.shows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mbappe.models.Show

@Composable
internal fun ShowsRoute(
    modifier: Modifier = Modifier,
    showViewModel: ShowsViewModel = hiltViewModel(),
) {
    val showsPagingItems: LazyPagingItems<Show> = showViewModel.flow.collectAsLazyPagingItems()

    stationsScreen(
        showsPagingItems = showsPagingItems,
        modifier = modifier
    )
}

@Composable
private fun stationsScreen(
    showsPagingItems: LazyPagingItems<Show>,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        if (showsPagingItems.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = state,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(count = showsPagingItems.itemCount) { index ->
                    showsPagingItems[index]?.let { show ->
                        Text(index.toString(), fontSize = 20.sp)
                        Text(show.id, fontSize = 10.sp)
                        Text(show.title, fontSize = 20.sp)
                        HorizontalDivider()
                    }
                }

                item {
                    if (showsPagingItems.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}
