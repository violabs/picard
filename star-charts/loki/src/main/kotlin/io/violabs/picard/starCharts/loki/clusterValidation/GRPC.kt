package io.violabs.picard.starCharts.loki.clusterValidation

import io.violabs.picard.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class GRPC(
    /**
     *   grpc:
     *     # When enabled, cluster label validation is executed: configured cluster
     *     # validation label is compared with the cluster validation label received
     *     # through the requests.
     *     # CLI flag: -server.cluster-validation.grpc.enabled
     *     [enabled: <boolean> | default = false]
     */
    val enabled: Boolean? = null,
    /**
     *     # When enabled, soft cluster label validation is executed. Can be enabled
     *     # only together with server.cluster-validation.grpc.enabled
     *     # CLI flag: -server.cluster-validation.grpc.soft-validation
     *     [soft_validation: <boolean> | default = false]
     */
    val softValidation: Boolean? = null
)