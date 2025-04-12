package io.violabs.picard.domain.k8sResources.workload.pod.gate

import io.violabs.picard.domain.DslBuilder

data class SchedulingGate(val name: String) {
    class Builder : DslBuilder<SchedulingGate> {
        var name: String? = null

        override fun build(): SchedulingGate {
            return SchedulingGate(
                name = requireNotNull(name)
            )
        }
    }
}