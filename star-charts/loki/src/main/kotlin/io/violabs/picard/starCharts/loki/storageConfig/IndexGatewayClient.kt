package io.violabs.picard.starCharts.loki.storageConfig

import com.fasterxml.jackson.annotation.JsonProperty
import io.violabs.picard.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.GrpcClient

@GeneratedDsl
data class IndexGatewayClient(
    /**
     *     # The grpc_client block configures the gRPC client used to communicate
     *     # between a client and server component in Loki.
     *     # The CLI flags prefix for this block configuration is:
     *     # boltdb.shipper.index-gateway-client.grpc
     *     [grpc_client_config: <grpc_client>]
     */
    val grpcClientConfig: GrpcClient? = null,
    /**
     *     # Hostname or IP of the Index Gateway gRPC server running in simple mode.
     *     # Can also be prefixed with dns+, dnssrv+, or dnssrvnoa+ to resolve a DNS A
     *     # record with multiple IP's, a DNS SRV record with a followup A record
     *     # lookup, or a DNS SRV record without a followup A record lookup,
     *     # respectively.
     *     # CLI flag: -boltdb.shipper.index-gateway-client.server-address
     *     [server_address: <string> | default = ""]
     */
    val serverAddress: String? = null,
    /**
     *     # Whether requests sent to the gateway should be logged or not.
     *     # CLI flag: -boltdb.shipper.index-gateway-client.log-gateway-requests
     *     [log_gateway_requests: <boolean> | default = false]
     */
    val logGatewayRequests: Boolean? = null,
    /**
     *   [ingestername: <string> | default = ""]
     */
    @JsonProperty("ingestername")
    val ingesterName: String? = null,
    /**
     *   [mode: <string> | default = ""]
     */
    val mode: String? = null,
    /**
     *   [ingesterdbretainperiod: <duration>]
     */
    @JsonProperty("ingesterdbretainperiod")
    val ingesterDbRetainPeriod: Duration? = null,

    /**
     *   # Build per tenant index files
     *   # CLI flag: -boltdb.shipper.build-per-tenant-index
     *   [build_per_tenant_index: <boolean> | default = false]
     */
    val buildPerTenantIndex: Boolean? = null
)