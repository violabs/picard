package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.OpaqueDeviceConfiguration

data class DeviceAllocationConfiguration(
    val source: String,
    val opaque: OpaqueDeviceConfiguration? = null,
    val request: List<String>? = null
) {
    class Builder : DSLBuilder<DeviceAllocationConfiguration> {
        var source: String? = null
        private var opaque: OpaqueDeviceConfiguration? = null
        private var requests: List<String>? = null

        fun opaque(block: OpaqueDeviceConfiguration.Builder.() -> Unit) {
            opaque = OpaqueDeviceConfiguration.Builder().apply(block).build()
        }

        fun requests(vararg request: String) {
            this.requests = request.toList()
        }

        override fun build(): DeviceAllocationConfiguration {
            return DeviceAllocationConfiguration(
                source = vRequireNotNull(this::source),
                opaque = opaque,
                request = requests
            )
        }
    }

    class Group : BuilderGroup<DeviceAllocationConfiguration, Builder>(Builder()) {
        fun configs(): List<DeviceAllocationConfiguration>? = items()

        fun configDetails(block: Builder.() -> Unit) {
            add(block)
        }
    }
}