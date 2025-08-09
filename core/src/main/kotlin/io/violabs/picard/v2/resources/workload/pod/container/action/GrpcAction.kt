package io.violabs.picard.v2.resources.workload.pod.container.action

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * GRPCAction describes an action involving a GRPC port.
 */
@GeneratedDsl
data class GrpcAction(
    /**
     * Port number of the gRPC service. Number must be in the range 1 to 65535.
     */
    val port: Int,
    /**
     * Service is the name of the service to place in the gRPC HealthCheckRequest
     * (see https://github.com/grpc/grpc/blob/master/doc/health-checking.md).
     *
     * If this is not specified, the default behavior is defined by gRPC.
     */
    val service: String? = null
)