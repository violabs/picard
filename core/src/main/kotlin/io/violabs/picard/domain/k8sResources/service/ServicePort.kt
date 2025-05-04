package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BasePort
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Protocol

data class ServicePort(
    val ports: Int,
    val targetPort: IntOrString? = null,
    val protocol: Protocol? = null,
    val name: String? = null,
    val nodePort: Int? = null,
    val appProtocol: String? = null
) : BasePort {
    class Builder : DSLBuilder<ServicePort> {
        var ports: Int? = null
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
                ports = vRequireNotNull(this::ports),
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

        fun port(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}