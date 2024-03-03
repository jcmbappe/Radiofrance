package com.mbappe.radiofrance.ui.component.organisms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.mbappe.models.Show
import com.mbappe.radiofrance.ui.component.molecules.ShowCardMolecule

fun LazyListScope.showCardItems(
    modifier: Modifier = Modifier,
    showsPagingItems: LazyPagingItems<Show>,
    stationColor: Color
) = items(count = showsPagingItems.itemCount) { index ->
    showsPagingItems[index]?.let { show ->
        ShowCardMolecule(modifier = modifier, show = show, stationColor = stationColor)
        Spacer(modifier = Modifier.height(15.dp))
    }
}