package io.violabs.picard.domain

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.k8sResources.Quantity

data class ResourceFieldSelector(
    val resource: String,
    val containerName: String,
    val divisor: Quantity? = null
) {
    class Builder : DSLBuilder<ResourceFieldSelector> {
        var resource: String? = null
        var containerName: String? = null
        private var divisor: Quantity? = null

        fun divisor(value: String) {
            divisor = Quantity(value)
        }

        override fun build(): ResourceFieldSelector {
            return ResourceFieldSelector(
                resource = vRequireNotNull(this::resource),
                containerName = vRequireNotNull(this::containerName),
                divisor = divisor
            )
        }
    }
}