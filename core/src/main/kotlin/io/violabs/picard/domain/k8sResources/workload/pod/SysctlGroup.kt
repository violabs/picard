package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.domain.BuilderGroup

class SysctlGroup : BuilderGroup<Sysctl, Sysctl.Builder>(Sysctl.Builder()) {
    fun sysctls(): MutableList<Sysctl>? = items()

    fun sysctl(scope: Sysctl.Builder.() -> Unit) {
        add(scope)
    }
}