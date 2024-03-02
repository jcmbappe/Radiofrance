package com.mbappe.models

data class Show(
    val id: String,
    val title: String,
    val url: String,
    val imageSquareUrl: String,
    val podcasts: List<Podcast>,
    val themes: List<Theme>
)
