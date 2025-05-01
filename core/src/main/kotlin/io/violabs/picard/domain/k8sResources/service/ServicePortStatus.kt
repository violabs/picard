package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.Protocol

data class ServicePortStatus(
    val port: Int? = null,
    val protocol: Protocol? = null,
    val error: String? = null
) {
    class Builder : DSLBuilder<ServicePortStatus> {
        var port: Int? = null
        var protocol: Protocol? = null
        var error: String? = null

        override fun build(): ServicePortStatus {
            return ServicePortStatus(
                port = port,
                protocol = protocol,
                error = error
            )
        }
    }

    class Group : BuilderGroup<ServicePortStatus, Builder>(Builder()) {
        fun ports(): List<ServicePortStatus>? = items()

        fun port(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}