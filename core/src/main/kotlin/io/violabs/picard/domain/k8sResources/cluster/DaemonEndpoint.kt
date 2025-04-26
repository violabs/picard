package io.violabs.picard.domain.k8sResources.cluster

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

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
