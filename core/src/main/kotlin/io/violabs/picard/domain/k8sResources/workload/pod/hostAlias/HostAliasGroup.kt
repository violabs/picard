package io.violabs.picard.domain.k8sResources.workload.pod.hostAlias

import io.violabs.picard.common.BuilderGroup

class HostAliasGroup : BuilderGroup<HostAlias, HostAlias.Builder>(HostAlias.Builder()) {
    fun hostAliases(): MutableList<HostAlias>? = items()

    fun hostAlias(scope: HostAlias.Builder.() -> Unit) {
        add(scope)
    }
}