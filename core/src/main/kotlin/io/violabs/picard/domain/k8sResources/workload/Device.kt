package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class Device(
    val name: String,
    val basic: DeviceBasic? = null
) {
    class Builder : DSLBuilder<Device> {
        var name: String? = null
        private var basic: DeviceBasic? = null

        fun basic(block: DeviceBasic.Builder.() -> Unit) {
            basic = DeviceBasic.Builder().apply(block).build()
        }

        override fun build(): Device {
            return Device(
                name = requireNotNull(name),
                basic = basic
            )
        }
    }

    class Group : BuilderGroup<Device, Builder>(Builder()) {
        fun devices(): List<Device>? = items()

        fun device(block: Builder.() -> Unit) {
            add(block)
        }
    }
}