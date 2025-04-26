package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.cluster.DaemonEndpoint

data class DaemonEndpoints(
    val kubeletEndpoint: DaemonEndpoint? = null
) {
    class Builder : DSLBuilder<DaemonEndpoints> {
        private var daemonEndpoint: DaemonEndpoint? = null

        fun daemonEndpoint(scope: DaemonEndpoint.Builder.() -> Unit) {
            daemonEndpoint = DaemonEndpoint.Builder().apply(scope).build()
        }

        override fun build(): DaemonEndpoints {
            return DaemonEndpoints(daemonEndpoint)
        }
    }
}