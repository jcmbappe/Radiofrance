package com.mbappe.models

data class Theme(
    val title: String,
    val relation: Relation
) {
    enum class Relation {
        THEME, SUB_THEME, UNKNOWN
    }
}
