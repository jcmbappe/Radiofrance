package com.mbappe.radiofrance.ui.component.atoms

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoaderAtom(
    modifier: Modifier,
    @RawRes rawRes: Int = com.mbappe.common.R.raw.radio_france_white
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}