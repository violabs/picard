package io.violabs.picard.domain.k8sResources.workload

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class Device(
    val name: String,
    val basic: DeviceBasic? = null
) {
    class Builder : DslBuilder<Device> {
        var name: String? = null
        private var basic: DeviceBasic? = null

        fun basic(block: DeviceBasic.Builder.() -> Unit) {
            basic = DeviceBasic.Builder().apply(block).build()
        }

        override fun build(): Device {
            return Device(
                name = vRequireNotNull(this::name),
                basic = basic
            )
        }
    }

    class Group : BuilderGroup<Device, Builder>(Builder()) {
        fun devices(): List<Device>? = items()

        fun addDevice(block: Builder.() -> Unit) {
            add(block)
        }
    }
}