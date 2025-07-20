package io.violabs.picard.domain.k8sResources.service

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BasePort
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.serialization.IntOrStringSerializer

data class ServicePort(
    val port: Int,
    @get:JsonSerialize(using = IntOrStringSerializer::class)
    val targetPort: IntOrString? = null,
    val protocol: Protocol? = null,
    val name: String? = null,
    val nodePort: Int? = null,
    val appProtocol: String? = null
) : BasePort {
    class Builder : DslBuilder<ServicePort> {
        var port: Int? = null
        private var targetPort: IntOrString? = null
        var protocol: Protocol? = null
        var name: String? = null
        var nodePort: Int? = null
        var appProtocol: String? = null

        fun targetPort(port: Int) {
            targetPort = IntOrString(port)
        }

        fun targetPort(port: String) {
            targetPort = IntOrString(str = port)
        }

        override fun build(): ServicePort {
            return ServicePort(
                port = vRequireNotNull(this::port),
                targetPort = targetPort,
                protocol = protocol,
                name = name,
                nodePort = nodePort,
                appProtocol = appProtocol
            )
        }
    }

    class Group : BuilderGroup<ServicePort, Builder>(Builder()) {
        fun ports(): List<ServicePort>? = items()

        fun addServicePort(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}