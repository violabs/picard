package io.violabs.picard.starCharts.loki.ingesterClient

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.starCharts.loki.Duration
import io.violabs.picard.starCharts.loki.GrpcClient

@GeneratedDsl
data class IngesterClient(
    /**
     * # Configures how connections are pooled.
     */
    val poolConfig: PoolConfig? = null,
    /**
     * # The remote request timeout on the client side.
     * # CLI flag: -ingester.client.timeout
     * [remote_timeout: <duration> | default = 5s]
     */
    val remoteTimeout: Duration? = null,
    /**
     * # Configures how the gRPC connection to ingesters work as a client.
     * # The CLI flags prefix for this block configuration is: ingester.client
     * [grpc_client_config: <grpc_client>]
     */
    val grpcClientConfig: GrpcClient? = null
)