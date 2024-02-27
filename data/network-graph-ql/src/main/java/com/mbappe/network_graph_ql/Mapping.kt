package com.mbappe.network_graph_ql

import com.mbappe.models.Brand
import com.mbappe.models.Show
import com.mbappe.radiofrance.GetBrandsQuery
import com.mbappe.radiofrance.GetShowsQuery

fun GetBrandsQuery.Brand.toBrand() = Brand(
    id = id,
    title = title,
    baseline = baseline,
    description = description
)

fun GetShowsQuery.Node.toShow() = Show(
    id = id,
    title = title,
)