package io.violabs.picard.starCharts.loki.bloomGateway

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.GrpcClient

@GeneratedDsl
data class BloomGatewayClient(
    /**
     * Configures the behavior of the connection pool.
     */
    val poolConfig: PoolConfig? = null,
    /**
     * # The grpc_client block configures the gRPC client used to communicate between
     * # a client and server component in Loki.
     * # The CLI flags prefix for this block configuration is:
     * # bloom-gateway-client.grpc
     * [grpc_client_config: <grpc_client>]
     */
    val grpcClientConfig: GrpcClient? = null,
    /**
     * # Comma separated addresses list in DNS Service Discovery format:
     * # https://grafana.com/docs/mimir/latest/configure/about-dns-service-discovery/#supported-discovery-modes
     * # CLI flag: -bloom-gateway-client.addresses
     * [addresses: <string> | default = ""]
     */
    val address: String? = null
)