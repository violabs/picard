package io.violabs.picard.starCharts.loki.bloomBuild

import io.violabs.picard.dsl.annotation.GeneratedDSL

@GeneratedDSL
data class BloomBuildBuilder(
    /**
     * # The grpc_client block configures the gRPC client used to communicate between
     * # a client and server component in Loki.
     * # The CLI flags prefix for this block configuration is:
     * # bloom-build.builder.grpc
     * [grpc_config: <grpc_client>]
     */
    val grpcConfig: String? = null,
    /**
     *
     * # Hostname (and port) of the bloom planner
     * # CLI flag: -bloom-build.builder.planner-address
     * [planner_address: <string> | default = ""]
     */
    val plannerAddress: String? = null,
    val backoffConfig: BloomBuildBuilderBackoffConfig? = null
)