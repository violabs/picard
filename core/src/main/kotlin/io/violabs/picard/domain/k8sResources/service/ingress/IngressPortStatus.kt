package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.Protocol

data class IngressPortStatus(
    val port: Int? = null,
    val protocol: Protocol? = null,
    val error: String? = null
) {
    class Builder : DSLBuilder<IngressPortStatus> {
        var port: Int? = null
        var protocol: Protocol? = null
        var error: String? = null

        override fun build(): IngressPortStatus {
            return IngressPortStatus(
                port = port,
                protocol = protocol,
                error = error
            )
        }
    }

    class Group : BuilderGroup<IngressPortStatus, Builder>(Builder()) {
        fun statuses(): List<IngressPortStatus>? = items()

        fun status(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}