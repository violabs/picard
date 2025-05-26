package io.violabs.picard.starCharts.loki.clusterValidation

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

@GeneratedDsl
data class ClusterValidation(
    /**
     *   # Optionally define the cluster validation label.
     *   # CLI flag: -server.cluster-validation.label
     *   [label: <string> | default = ""]
     */
    val label: String? = null,
    val grpc: GRPC? = null,
)