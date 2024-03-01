package com.mbappe.network_graph_ql

import com.mbappe.models.Station
import com.mbappe.models.Show
import com.mbappe.radiofrance.GetBrandsQuery
import com.mbappe.radiofrance.GetShowsQuery

fun GetBrandsQuery.Brand.toBrand() = Station(
    id = id,
    title = title,
    baseline = baseline,
    description = description
)

fun GetShowsQuery.Node.toShow() = Show(
    id = id,
    title = title,
)