package io.violabs.picard.domain.k8sResources.policy.networkPolicy

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.IntOrString
import io.violabs.picard.domain.k8sResources.Protocol
import io.violabs.picard.serialization.IntOrStringSerializer

data class NetworkPolicyPort(
    @get:JsonSerialize(using = IntOrStringSerializer::class)
    val port: IntOrString? = null,
    val endPort: Int? = null,
    val protocol: Protocol? = null
) {
    class Builder : DSLBuilder<NetworkPolicyPort> {
        private var port: IntOrString? = null
        var endPort: Int? = null
        var protocol: Protocol? = null

        fun port(port: Int) {
            this.port = IntOrString(num = port)
        }

        fun port(port: String) {
            this.port = IntOrString(str = port)
        }

        override fun build(): NetworkPolicyPort {
            return NetworkPolicyPort(
                port = port,
                endPort = endPort,
                protocol = protocol
            )
        }
    }

    class Group : BuilderGroup<NetworkPolicyPort, Builder>(Builder()) {
        fun ports(): List<NetworkPolicyPort>? = items()

        fun port(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}