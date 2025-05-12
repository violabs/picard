package io.violabs.picard.starCharts.loki.common

import io.violabs.picard.starCharts.loki.common.LokiDSL

@LokiDSL
interface DSLBuilder<T> {
    fun build(): T
}