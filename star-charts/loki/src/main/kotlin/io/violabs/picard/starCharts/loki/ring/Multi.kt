package io.violabs.picard.starCharts.loki.ring

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.Duration

@GeneratedDSL
data class Multi(
    /**
     * # Primary backend storage used by multi-client.
     * # CLI flag: -index-gateway.ring.multi.primary
     * [primary: <string> | default = ""]
     */
    val primary: String? = null,
    /**
     * # Secondary backend storage used by multi-client.
     * # CLI flag: -index-gateway.ring.multi.secondary
     * [secondary: <string> | default = ""]
     */
    val secondary: String? = null,
    /**
     * # Mirror writes to secondary store.
     * # CLI flag: -index-gateway.ring.multi.mirror-enabled
     * [mirror_enabled: <boolean> | default = false]
     */
    val mirrorEnabled: Boolean? = null,
    /**
     * # Timeout for storing value to secondary store.
     * # CLI flag: -index-gateway.ring.multi.mirror-timeout
     * [mirror_timeout: <duration> | default = 2s]
     */
    val mirrorTimeout: Duration? = null,
)