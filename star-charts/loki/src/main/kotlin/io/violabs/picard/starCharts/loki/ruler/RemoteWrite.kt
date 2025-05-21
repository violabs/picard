package io.violabs.picard.starCharts.loki.ruler

import io.violabs.picard.starCharts.loki.Duration

data class RemoteWrite(
    /**
     *   # Deprecated: Use 'clients' instead. Configure remote write client.
     *   [client: <RemoteWriteConfig>]
     */
    val client: Any? = null,
    /**
     *   # Configure remote write clients. A map with remote client id as key. For
     *   # details, see
     *   # https://prometheus.io/docs/prometheus/latest/configuration/configuration/#remote_write
     *   # Specifying a header with key 'X-Scope-OrgID' under the 'headers' section of
     *   # RemoteWriteConfig is not permitted. If specified, it will be dropped during
     *   # config parsing.
     *   [clients: <map of string to RemoteWriteConfig>]
     */
    val clients: Map<String, Any>? = null,
    /**
     *   # Enable remote-write functionality.
     *   # CLI flag: -ruler.remote-write.enabled
     *   [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    /**
     *   # Minimum period to wait between refreshing remote-write reconfigurations.
     *   # This should be greater than or equivalent to
     *   # -limits.per-user-override-period.
     *   # CLI flag: -ruler.remote-write.config-refresh-period
     *   [config_refresh_period: <duration> | default = 10s]
     */
    val configRefreshPeriod: Duration? = null,
    /**
     *   # Add an X-Scope-OrgID header in remote write requests with the tenant ID of a
     *   # Loki tenant that the recording rules are part of.
     *   # CLI flag: -ruler.remote-write.add-org-id-header
     *   [add_org_id_header: <boolean> | default = true]
     */
    val addOrgIdHeader: Boolean? = null
)