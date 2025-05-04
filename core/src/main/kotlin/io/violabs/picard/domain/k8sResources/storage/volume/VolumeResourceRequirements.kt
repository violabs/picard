package io.violabs.picard.domain.k8sResources.storage.volume

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class VolumeResourceRequirements(
    val limits: Map<String, Quantity>? = null,
    val requests: Map<String, Quantity>? = null
) {
    class Builder : DSLBuilder<VolumeResourceRequirements> {
        private var limits: Map<String, Quantity>? = null
        private var requests: Map<String, Quantity>? = null

        fun requests(vararg requests: Pair<String, String>) {
            this.requests = requests.associate { (k, v) -> k to Quantity(v) }
        }

        fun limits(vararg limits: Pair<String, String>) {
            this.limits = limits.associate { (k, v) -> k to Quantity(v) }
        }

        override fun build(): VolumeResourceRequirements {
            return VolumeResourceRequirements(
                limits = limits,
                requests = requests
            )
        }
    }
}