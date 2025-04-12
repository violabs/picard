package io.violabs.picard.domain.k8sResources.workload.pod.gate

import io.violabs.picard.domain.DslBuilder

data class ReadinessGate(val conditionType: String) {
    class Builder : DslBuilder<ReadinessGate> {
        var conditionType: String? = null
        override fun build(): ReadinessGate {
            return ReadinessGate(
                conditionType = requireNotNull(conditionType)
            )
        }
    }
}