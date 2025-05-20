package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class IndexGateway(
    /**
     * # Defines in which mode the index gateway server will operate (default to
     * # 'simple'). It supports two modes:
     * # - 'simple': an index gateway server instance is responsible for handling,
     * # storing and returning requests for all indices for all tenants.
     * # - 'ring': an index gateway server instance is responsible for a subset of
     * # tenants instead of all tenants.
     * # CLI flag: -index-gateway.mode
     * [mode: <string> | default = "simple"]
     */
    val mode: String? = null,
    /**
     * # Defines the ring to be used by the index gateway servers and clients in case
     * # the servers are configured to run in 'ring' mode. In case this isn't
     * # configured, this block supports inheriting configuration from the common ring
     * # section.
     */
    val ring: indexGateway.Ring? = null,
)
