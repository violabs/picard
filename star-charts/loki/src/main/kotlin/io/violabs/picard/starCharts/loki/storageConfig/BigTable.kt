package io.violabs.picard.starCharts.loki.storageConfig

import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.GrpcClient
import javax.annotation.processing.Generated

@Generated
data class BigTable(
    /**
     *   # Bigtable project ID.
     *   # CLI flag: -bigtable.project
     *   [project: <string> | default = ""]
     */
    val project: String? = null,
    /**
     *   # Bigtable instance ID. Please refer to
     *   # https://cloud.google.com/docs/authentication/production for more information
     *   # about how to configure authentication.
     *   # CLI flag: -bigtable.instance
     *   [instance: <string> | default = ""]
     */
    val instance: String? = null,
    /**
     *   # The grpc_client block configures the gRPC client used to communicate between
     *   # a client and server component in Loki.
     *   # The CLI flags prefix for this block configuration is: bigtable
     *   [grpc_client_config: <grpc_client>]
     */
    val grpcClientConfig: GrpcClient? = null,
    /**
     *   # If enabled, once a tables info is fetched, it is cached.
     *   # CLI flag: -bigtable.table-cache.enabled
     *   [table_cache_enabled: <boolean> | default = true]
     */
    val tableCacheEnabled: Boolean? = null,
    /**
     *   # Duration to cache tables before checking again.
     *   # CLI flag: -bigtable.table-cache.expiration
     *   [table_cache_expiration: <duration> | default = 30m]
     */
    val tableCacheExpiration: Duration? = null,
)