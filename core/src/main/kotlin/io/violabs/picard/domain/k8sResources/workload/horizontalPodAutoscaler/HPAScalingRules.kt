package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.domain.DSLBuilder

data class HPAScalingRules(
    val policies: List<HPAScalingPolicy>? = null,
    val selectPolicy: String? = null,
    val stabilizationWindowSeconds: Int? = null
) {
    class Builder : DSLBuilder<HPAScalingRules> {
        private var policies: List<HPAScalingPolicy>? = null
        var selectPolicy: String? = null
        var stabilizationWindowSeconds: Int? = null


        fun policies(block: HPAScalingPolicy.Group.() -> Unit) {
            policies = HPAScalingPolicy.Group().apply(block).policies()
        }

        override fun build(): HPAScalingRules {
            return HPAScalingRules(
                policies = policies,
                selectPolicy = selectPolicy,
                stabilizationWindowSeconds = stabilizationWindowSeconds
            )
        }
    }
}