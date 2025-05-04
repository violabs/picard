package io.violabs.picard.domain.k8sResources.cluster.node

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotNull

data class DaemonEndpoint(
    val port: Int
) {
    class Builder : DSLBuilder<DaemonEndpoint> {
        var port: Int? = null

        override fun build(): DaemonEndpoint {
            return DaemonEndpoint(vRequireNotNull(this::port))
        }
    }
}