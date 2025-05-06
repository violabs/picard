package io.violabs.picard.domain.k8sResources.service.endpoint

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.ObjectReference

data class EndpointAddress(
    val ip: String,
    val hostname: String? = null,
    val nodeName: String? = null,
    val targetRef: ObjectReference? = null
) {
    class Builder : DSLBuilder<EndpointAddress> {
        var ip: String? = null
        var hostname: String? = null
        var nodeName: String? = null
        private var targetRef: ObjectReference? = null

        fun targetRef(scope: ObjectReference.Builder.() -> Unit) {
            targetRef = ObjectReference.Builder().apply(scope).build()
        }

        override fun build(): EndpointAddress {
            return EndpointAddress(
                ip = vRequireNotNull(this::ip),
                hostname = hostname,
                nodeName = nodeName,
                targetRef = targetRef
            )
        }
    }

    class Group : BuilderGroup<EndpointAddress, Builder>(Builder()) {
        fun addresses(): List<EndpointAddress>? = items()

        fun addEndpointAddress(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}