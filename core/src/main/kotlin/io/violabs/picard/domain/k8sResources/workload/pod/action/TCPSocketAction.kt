package io.violabs.picard.domain.k8sResources.workload.pod.action

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.serialization.IntOrStringSerializer

data class TCPSocketAction(
    @get:JsonSerialize(using = IntOrStringSerializer::class)
    val port: IntOrString,
    val host: String? = null
) {
    class Builder : DSLBuilder<TCPSocketAction> {
        private var port: IntOrString? = null
        var host: String? = null

        fun port(port: Int) {
            this.port = IntOrString(port)
        }

        fun port(port: String) {
            this.port = IntOrString(str = port)
        }

        override fun build(): TCPSocketAction {
            return TCPSocketAction(
                port = vRequireNotNull(this::port),
                host
            )
        }
    }
}