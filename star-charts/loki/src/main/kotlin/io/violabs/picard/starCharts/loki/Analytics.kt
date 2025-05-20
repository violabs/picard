package io.violabs.picard.starCharts.loki

import io.violabs.picard.dsl.annotation.GeneratedDSL
import io.violabs.picard.starCharts.loki.tls.TLSConfig

@GeneratedDSL
class Analytics(
    /**
     * # Enable anonymous usage reporting.
     * # CLI flag: -reporting.enabled
     * [reporting_enabled: <boolean> | default = true]
     */
    val reportingEnabled: Boolean? = null,
    /**
     * # URL to which reports are sent
     * # CLI flag: -reporting.usage-stats-url
     * [usage_stats_url: <string> | default = "https://stats.grafana.org/loki-usage-report"]
     */
    val usageStatsUrl: String? = null,
    /**
     * # URL to the proxy server
     * # CLI flag: -reporting.proxy-url
     * [proxy_url: <string> | default = ""]
     */
    val proxyUrl: String? = null,
    /**
     * # The TLS configuration.
     * # The CLI flags prefix for this block configuration is: reporting.tls-config
     * [tls_config: <tls_config>]
     */
    val tlsConfig: TLSConfig? = null
)