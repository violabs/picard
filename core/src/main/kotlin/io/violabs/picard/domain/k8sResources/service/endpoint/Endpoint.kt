package io.violabs.picard.domain.k8sResources.service.endpoint

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.ObjectReference


data class Endpoint(
    val addresses: List<String>? = null,
    val conditions: List<EndpointCondition>? = null,
    val hints: EndpointHints? = null,
    val hostname: String? = null,
    val nodeName: String? = null,
    val targetRef: ObjectReference? = null,
    val zone: String? = null
) {
    class Builder : DSLBuilder<Endpoint> {
        private var addresses: List<String>? = null
        private var conditions: List<EndpointCondition>? = null
        private var hints: EndpointHints? = null
        var hostname: String? = null
        var nodeName: String? = null
        private var targetRef: ObjectReference? = null
        var zone: String? = null

        fun addresses(vararg addresses: String) {
            this.addresses = addresses.toList()
        }

        fun conditions(scope: EndpointCondition.Group.() -> Unit) {
            this.conditions = EndpointCondition.Group().apply(scope).conditions()
        }

        fun hints(scope: EndpointHints.Builder.() -> Unit) {
            this.hints = EndpointHints.Builder().apply(scope).build()
        }

        fun targetRef(scope: ObjectReference.Builder.() -> Unit) {
            this.targetRef = ObjectReference.Builder().apply(scope).build()
        }

        override fun build(): Endpoint {
            return Endpoint(
                addresses = addresses,
                conditions = conditions,
                hints = hints,
                hostname = hostname,
                nodeName = nodeName,
                targetRef = targetRef,
                zone = zone
            )
        }
    }

    class Group : BuilderGroup<Endpoint, Builder>(Builder()) {
        fun endpoints(): List<Endpoint>? = items()

        fun endpoint(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}