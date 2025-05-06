package io.violabs.picard.domain.k8sResources.workload.pod.resource

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class ResourceHealth(
    val resourceID: String,
    val health: Type? = null
) {
    enum class Type {
        Healthy,
        Unhealthy,
        Unknown
    }

    class Builder : DSLBuilder<ResourceHealth> {
        var resourceID: String? = null
        var health: Type? = null

        override fun build(): ResourceHealth {
            return ResourceHealth(
                resourceID = vRequireNotNull(this::resourceID),
                health = health
            )
        }
    }

    class Group : BuilderGroup<ResourceHealth, Builder>(Builder()) {
        fun resourceHealths(): List<ResourceHealth>? = items()

        fun addResourceHealth(block: Builder.() -> Unit) {
            add(block)
        }
    }
}