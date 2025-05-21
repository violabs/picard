package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL(
    withGroup = true
)
data class Header(val key: String, val values: List<String>)