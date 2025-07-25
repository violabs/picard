package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.DslBuilder

data class DaemonEndpoints(
    val kubeletEndpoint: DaemonEndpoint? = null
) {
    class Builder : DslBuilder<DaemonEndpoints> {
        private var daemonEndpoint: DaemonEndpoint? = null

        fun daemonEndpoint(scope: DaemonEndpoint.Builder.() -> Unit) {
            daemonEndpoint = DaemonEndpoint.Builder().apply(scope).build()
        }

        override fun build(): DaemonEndpoints {
            return DaemonEndpoints(daemonEndpoint)
        }
    }
}