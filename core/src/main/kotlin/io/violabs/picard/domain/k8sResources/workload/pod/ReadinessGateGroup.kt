package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.BuilderGroup

class ReadinessGateGroup : BuilderGroup<ReadinessGate, ReadinessGate.Builder>(ReadinessGate.Builder()) {
    fun readinessGates(): MutableList<ReadinessGate>? = items()

    fun readinessGate(scope: ReadinessGate.Builder.() -> Unit) {
        add(scope)
    }
}
