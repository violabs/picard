package io.violabs.picard.domain.k8sResources.workload.horizontalPodAutoscaler

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class HPAScalingPolicy(
    val type: String,
    val value: Int,
    val periodSeconds: Int
) {
    class Builder : DslBuilder<HPAScalingPolicy> {
        var type: String? = null
        var value: Int? = null
        var periodSeconds: Int? = null

        override fun build(): HPAScalingPolicy {
            return HPAScalingPolicy(
                type = vRequireNotNull(this::type),
                value = vRequireNotNull(this::value),
                periodSeconds = vRequireNotNull(this::periodSeconds)
            )
        }
    }

    class Group : BuilderGroup<HPAScalingPolicy, Builder>(Builder()) {
        fun policies(): List<HPAScalingPolicy>? = items()

        fun addHPAScalingPolicy(block: Builder.() -> Unit) {
            add(block)
        }
    }
}