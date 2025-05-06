package io.violabs.picard.domain.k8sResources.extend.deviceClass

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.OpaqueDeviceConfiguration

data class DeviceClassConfiguration(
    val opaque: OpaqueDeviceConfiguration? = null
) {
    class Builder : DSLBuilder<DeviceClassConfiguration> {
        private var opaque: OpaqueDeviceConfiguration? = null

        fun opaque(opaque: OpaqueDeviceConfiguration.Builder.() -> Unit) {
            this.opaque = OpaqueDeviceConfiguration.Builder().apply(opaque).build()
        }

        override fun build(): DeviceClassConfiguration {
            return DeviceClassConfiguration(
                opaque = opaque
            )
        }
    }

    class Group : BuilderGroup<DeviceClassConfiguration, Builder>(Builder()) {
        fun configs(): List<DeviceClassConfiguration>? = items()

        fun addDeviceClassConfiguration(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}