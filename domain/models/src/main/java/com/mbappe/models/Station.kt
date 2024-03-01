package com.mbappe.models

data class Station(
    val id: String,
    val title: String,
    val baseline: String?,
    val description: String?,
    val imageUrl: String,
    val colorHex: Long
)