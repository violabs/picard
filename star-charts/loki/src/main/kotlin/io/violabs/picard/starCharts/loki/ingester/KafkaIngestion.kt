package io.violabs.picard.starCharts.loki.ingester

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.ring.Ring

@GeneratedDSL
data class KafkaIngestion(
    /**
     *   # Whether the kafka ingester is enabled.
     *   # CLI flag: -ingester.kafka-ingestion-enabled
     *   [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    val partitionRing: Ring? = null
)