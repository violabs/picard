package io.violabs.picard.starCharts.loki.bloomGateway

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class PoolConfig(
    /**
     *     # How frequently to update the list of servers.
     *     # CLI flag: -bloom-gateway-client.pool.check-interval
     *     [check_interval: <duration> | default = 15s]
     */
    val checkInterval: Duration? = null
)