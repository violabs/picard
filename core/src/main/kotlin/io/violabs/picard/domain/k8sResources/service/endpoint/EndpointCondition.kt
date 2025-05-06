package io.violabs.picard.domain.k8sResources.service.endpoint

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class EndpointCondition(
    val ready: Boolean? = null,
    val serving: Boolean? = null,
    val terminating: Boolean? = null
) {
    class Builder : DSLBuilder<EndpointCondition> {
        private var ready: Boolean? = null
        private var serving: Boolean? = null
        private var terminating: Boolean? = null

        fun ready(value: Boolean = true) {
            ready = value
        }

        fun serving(value: Boolean = true) {
            serving = value
        }

        fun terminating(value: Boolean = true) {
            terminating = value
        }

        override fun build(): EndpointCondition {
            return EndpointCondition(
                ready = ready,
                serving = serving,
                terminating = terminating
            )
        }
    }

    class Group : BuilderGroup<EndpointCondition, Builder>(Builder()) {
        fun conditions(): List<EndpointCondition>? = items()

        fun addEndpointCondition(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}