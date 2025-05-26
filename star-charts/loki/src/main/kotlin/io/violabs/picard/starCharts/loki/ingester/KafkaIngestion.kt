package io.violabs.picard.starCharts.loki.ingester

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.ring.Ring

@GeneratedDsl
data class KafkaIngestion(
    /**
     *   # Whether the kafka ingester is enabled.
     *   # CLI flag: -ingester.kafka-ingestion-enabled
     *   [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    val partitionRing: Ring? = null
)