package io.violabs.picard.domain.k8sResources.workload.pod.gate


import io.violabs.picard.common.BuilderGroup

class SchedulingGateGroup : BuilderGroup<SchedulingGate, SchedulingGate.Builder>(SchedulingGate.Builder()) {
    fun schedulingGates(): MutableList<SchedulingGate>? = items()

    fun schedulingGate(scope: SchedulingGate.Builder.() -> Unit) {
        add(scope)
    }
}