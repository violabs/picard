package io.violabs.picard.domain.k8sResources.workload.pod.gate

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class ReadinessGate(val conditionType: String) {
    class Builder : DSLBuilder<ReadinessGate> {
        var conditionType: String? = null
        override fun build(): ReadinessGate {
            return ReadinessGate(
                conditionType = vRequireNotNull(this::conditionType)
            )
        }
    }
}