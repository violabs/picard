package io.violabs.picard.domain.builder

import io.violabs.picard.domain.k8sResources.workload.pod.ReadinessGate

class ReadinessGatesBuilder : Builder<List<ReadinessGate>> {
    private val readinessGates: MutableList<ReadinessGate> = mutableListOf()

    override fun build(): List<ReadinessGate> = readinessGates

    fun readinessGate(scope: ReadinessGateBuilder.() -> Unit) {
        readinessGates.add(scopedBuild(::ReadinessGateBuilder, scope))
    }

}