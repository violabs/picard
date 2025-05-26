package io.violabs.picard.starCharts.loki.ingesterClient

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDsl
data class PoolConfig(
    /**
     *   # How frequently to clean up clients for ingesters that have gone away.
     *   # CLI flag: -distributor.client-cleanup-period
     *   [client_cleanup_period: <duration> | default = 15s]
     */
    val clientCleanupPeriod: Duration? = null,
    /**
     *   # Run a health check on each ingester client during periodic cleanup.
     *   # CLI flag: -distributor.health-check-ingesters
     *   [health_check_ingesters: <boolean> | default = true]
     */
    val healthCheckIngesters: Boolean? = null,
    /**
     *   # How quickly a dead client will be removed after it has been detected to
     *   # disappear. Set this to a value to allow time for a secondary health check to
     *   # recover the missing client.
     *   # CLI flag: -ingester.client.healthcheck-timeout
     *   [remote_timeout: <duration> | default = 1s]
     */
    val remoteTimeout: Duration? = null
)