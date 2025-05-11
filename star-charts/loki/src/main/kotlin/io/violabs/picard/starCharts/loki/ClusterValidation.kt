package io.violabs.picard.starCharts.loki

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ClusterValidation(
    /**
     *   # Optionally define the cluster validation label.
     *   # CLI flag: -server.cluster-validation.label
     *   [label: <string> | default = ""]
     */
    val label: String? = null,
    val grpc: GRPC? = null,
) {
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
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
}