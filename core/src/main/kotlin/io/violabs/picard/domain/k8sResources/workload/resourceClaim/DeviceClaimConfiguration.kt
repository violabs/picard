package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.OpaqueDeviceConfiguration

data class DeviceClaimConfiguration(
    val opaque: OpaqueDeviceConfiguration? = null,
    val requests: List<String>? = null
) {
    class Builder : DSLBuilder<DeviceClaimConfiguration> {
        private var opaque: OpaqueDeviceConfiguration? = null
        private var requests: List<String>? = null

        fun opaque(block: OpaqueDeviceConfiguration.Builder.() -> Unit) {
            opaque = OpaqueDeviceConfiguration.Builder().apply(block).build()
        }

        fun requests(vararg requests: String) {
            this.requests = requests.toList()
        }

        override fun build(): DeviceClaimConfiguration {
            return DeviceClaimConfiguration(
                opaque = opaque,
                requests = requests
            )
        }
    }
}