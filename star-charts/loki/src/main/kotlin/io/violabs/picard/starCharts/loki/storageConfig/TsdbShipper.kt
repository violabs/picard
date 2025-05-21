package io.violabs.picard.starCharts.loki.storageConfig

import io.violabs.picard.starCharts.loki.Duration
import javax.annotation.processing.Generated

@Generated
data class TsdbShipper(
    /**
     *   # Directory where ingesters would write index files which would then be
     *   # uploaded by shipper to configured storage
     *   # CLI flag: -tsdb.shipper.active-index-directory
     *   [active_index_directory: <string> | default = ""]
     */
    val activeIndexDirectory: String? = null,
    /**
     *   # Cache location for restoring index files from storage for queries
     *   # CLI flag: -tsdb.shipper.cache-location
     *   [cache_location: <string> | default = ""]
     */
    val cacheLocation: String? = null,
    /**
     *   # TTL for index files restored in cache for queries
     *   # CLI flag: -tsdb.shipper.cache-ttl
     *   [cache_ttl: <duration> | default = 24h]
     */
    val cacheTtl: Duration? = null,
    /**
     *   # Resync downloaded files with the storage
     *   # CLI flag: -tsdb.shipper.resync-interval
     *   [resync_interval: <duration> | default = 5m]
     */
    val resyncInterval: Duration? = null,
    /**
     *   # Number of days of common index to be kept downloaded for queries. For per
     *   # tenant index query readiness, use limits overrides config.
     *   # CLI flag: -tsdb.shipper.query-ready-num-days
     *   [query_ready_num_days: <int> | default = 0]
     */
    val queryReadyNumDays: Int? = null,
    val indexGatewayClient: IndexGatewayClient? = null
)