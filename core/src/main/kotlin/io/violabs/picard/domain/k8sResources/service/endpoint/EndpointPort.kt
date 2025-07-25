package io.violabs.picard.domain.k8sResources.service.endpoint

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BasePort
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.Protocol

data class EndpointPort(
    val port: Int,
    val protocol: Protocol? = null,
    val name: String? = null,
    val appProtocol: String? = null
) : BasePort {

    class Builder : DslBuilder<EndpointPort> {
        var port: Int? = null
        var protocol: Protocol? = null
        var name: String? = null
        var appProtocol: String? = null

        override fun build(): EndpointPort {
            return EndpointPort(
                port = vRequireNotNull(this::port),
                protocol = protocol,
                name = name,
                appProtocol = appProtocol
            )
        }
    }

    class Group : BuilderGroup<EndpointPort, Builder>(Builder()) {
        fun ports(): List<EndpointPort>? = items()

        fun addEndpointPort(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}