package io.violabs.picard.domain.k8sResources.workload.pod.gate

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class ReadinessGate(val conditionType: String) {
    class Builder : DslBuilder<ReadinessGate> {
        var conditionType: String? = null
        override fun build(): ReadinessGate {
            return ReadinessGate(
                conditionType = vRequireNotNull(this::conditionType)
            )
        }
    }

    class Group : BuilderGroup<ReadinessGate, Builder>(Builder()) {
        fun readinessGates(): List<ReadinessGate>? = items()

        fun addReadinessGate(block: Builder.() -> Unit) {
            add(block)
        }
    }
}