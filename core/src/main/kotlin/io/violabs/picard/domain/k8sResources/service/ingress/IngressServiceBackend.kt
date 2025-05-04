package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class IngressServiceBackend(
    val name: String,
    val port: IngressServiceBackendPort? = null
) {
    class Builder : DSLBuilder<IngressServiceBackend> {
        var name: String? = null
        private var port: IngressServiceBackendPort? = null

        fun port(block: IngressServiceBackendPort.Builder.() -> Unit) {
            port = IngressServiceBackendPort.Builder().apply(block).build()
        }

        override fun build(): IngressServiceBackend {
            return IngressServiceBackend(
                name = vRequireNotNull(this::name),
                port = port
            )
        }
    }
}