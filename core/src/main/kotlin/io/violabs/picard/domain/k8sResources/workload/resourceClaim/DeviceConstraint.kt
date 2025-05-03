package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class DeviceConstraint(
    val matchAttribute: String? = null,
    val requests: List<String>? = null
) {
    class Builder : DSLBuilder<DeviceConstraint> {
        var matchAttribute: String? = null
        private var requests: List<String>? = null

        fun requests(vararg requests: String) {
            this.requests = requests.toList()
        }

        override fun build(): DeviceConstraint {
            return DeviceConstraint(
                matchAttribute = matchAttribute,
                requests = requests
            )
        }
    }

    class Group : BuilderGroup<DeviceConstraint, Builder>(Builder()) {
        fun constraints(): List<DeviceConstraint>? = items()

        fun constraint(block: Builder.() -> Unit) {
            add(block)
        }
    }
}