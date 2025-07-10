package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Protocol

data class IngressPortStatus(
    val port: Int? = null,
    val protocol: Protocol? = null,
    val error: String? = null
) {
    class Builder : DslBuilder<IngressPortStatus> {
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

        fun addIngressPortStatus(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}