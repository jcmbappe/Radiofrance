package com.mbappe.radiofrance.shows

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val showList: LazyPagingItems<Show> = showViewModel.state.collectAsLazyPagingItems()

    stationsScreen(
        showList,
        modifier = modifier
    )
}

@Composable
private fun stationsScreen(
    lazyPagingItems: LazyPagingItems<Show>,
    modifier: Modifier = Modifier
) {
    val state = rememberLazyListState()

    Box(modifier = modifier) {


        LazyColumn(state = state) {


            when (lazyPagingItems.loadState.refresh) { //FIRST LOAD
                is LoadState.Error -> {
                    //TODO Error Item
                    //state.error to get error message
                }

                is LoadState.Loading -> { // Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading"
                            )

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                else -> {}
            }

            when (lazyPagingItems.loadState.append) { // Pagination
                is LoadState.Error -> {
                    //TODO Pagination Error Item
                    //state.error to get error message
                }

                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(text = "Pagination Loading")

                            CircularProgressIndicator(color = Color.Black)
                        }
                    }
                }

                else -> {}
            }

            items(count = lazyPagingItems.itemCount) { index ->
                lazyPagingItems[index]?.let { show ->
                    Text(show.id, fontSize = 20.sp)
                    Text(show.title, fontSize = 20.sp)
                    HorizontalDivider()
                }
            }
        }
    }
}
