package com.mbappe.network_graph_ql

import com.mbappe.models.Brand
import com.mbappe.radiofrance.GetBrandsQuery

fun GetBrandsQuery.Brand.toBrand() = Brand(
    id = id,
    title = title,
    baseline = baseline,
    description = description
)
