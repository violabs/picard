package io.violabs.picard.starCharts.loki.common

@LokiDSL
interface DSLBuilder<T> {
    fun build(): T
}