package io.violabs.picard.starCharts.loki.utils

@LokiDSL
interface DSLBuilder<T> {
    fun build(): T
}