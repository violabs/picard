package io.violabs.picard.domain.k8sResources.workload.pod.gate

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class SchedulingGate(val name: String) {
    class Builder : DSLBuilder<SchedulingGate> {
        var name: String? = null

        override fun build(): SchedulingGate {
            return SchedulingGate(
                name = vRequireNotNull(this::name)
            )
        }
    }
}