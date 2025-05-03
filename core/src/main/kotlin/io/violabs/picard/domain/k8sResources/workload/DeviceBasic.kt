package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.Quantity

data class DeviceBasic(
    val attributes: Map<String, DeviceAttribute>? = null,
    val capacity: Map<String, Quantity>? = null
) {
    class Builder : DSLBuilder<DeviceBasic> {
        private var attributes: Map<String, DeviceAttribute>? = null
        private var capacity: Map<String, Quantity>? = null

        fun attributes(block: DeviceAttribute.MapGroup.() -> Unit) {
            attributes = DeviceAttribute.MapGroup().apply(block).build()
        }

        fun capacity(vararg values: Pair<String, Quantity>) {
            capacity = values.toMap()
        }

        override fun build(): DeviceBasic {
            return DeviceBasic(
                attributes = attributes,
                capacity = capacity
            )
        }
    }
}