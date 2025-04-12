package io.violabs.picard.domain.k8sResources.workload.pod.action

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder
import io.violabs.picard.domain.k8sResources.IntOrString

data class TCPSocketAction(
    val port: IntOrString,
    val host: String? = null
) {
    class Builder : DslBuilder<TCPSocketAction> {
        private var internalPort: IntOrString? = null
        var host: String? = null

        fun port(): IntOrString? = internalPort

        fun port(port: Int) {
            this.internalPort = IntOrString(port)
        }

        fun port(port: String) {
            this.internalPort = IntOrString(str = port)
        }

        override fun build(): TCPSocketAction {
            return TCPSocketAction(
                port = vRequireNotNull(this::port),
                host
            )
        }
    }
}