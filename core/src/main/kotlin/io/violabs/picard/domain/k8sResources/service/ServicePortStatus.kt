package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
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
        fun statuses(): List<ServicePortStatus>? = items()

        fun addServicePortStatus(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}