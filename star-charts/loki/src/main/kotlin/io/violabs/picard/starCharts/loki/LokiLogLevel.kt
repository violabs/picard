package io.violabs.picard.starCharts.loki

enum class LokiLogLevel {
    DEBUG, INFO, WARN, ERROR;

    override fun toString(): String = name.lowercase()
}