package com.mbappe.radiofrance

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.emptyStates(modifier: Modifier = Modifier) {
    uiState(modifier = modifier, resource = R.drawable.radio_france_logo, text = "Empty state")
}

@Composable
fun BoxScope.errorStates(modifier: Modifier = Modifier, errorMessage: String?) {
    uiState(modifier = modifier, resource = R.drawable.baseline_error_24, text = errorMessage)
}

@Composable
private fun BoxScope.uiState(
    modifier: Modifier = Modifier,
    @DrawableRes resource: Int,
    text: String?
) {
    Column(
        modifier
            .fillMaxWidth()
            .align(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f),
            painter = painterResource(resource),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = text ?: "",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun previewEmptyState() {
    Box {
        emptyStates()
    }
}

@Preview
@Composable
private fun previewErrorState() {
    Box {
        errorStates(errorMessage = "Error")
    }
}