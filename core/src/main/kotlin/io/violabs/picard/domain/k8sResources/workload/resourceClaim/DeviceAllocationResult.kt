package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.DslBuilder

data class DeviceAllocationResult(
    val config: List<DeviceAllocationConfiguration>? = null,
    val results: List<DeviceRequestAllocationResult>? = null
) {
    class Builder : DslBuilder<DeviceAllocationResult> {
        private var config: List<DeviceAllocationConfiguration>? = null
        private var results: List<DeviceRequestAllocationResult>? = null

        fun config(block: DeviceAllocationConfiguration.Group.() -> Unit) {
            config = DeviceAllocationConfiguration.Group().apply(block).configs()
        }

        fun results(block: DeviceRequestAllocationResult.Group.() -> Unit) {
            results = DeviceRequestAllocationResult.Group().apply(block).results()
        }

        override fun build(): DeviceAllocationResult {
            return DeviceAllocationResult(
                config = config,
                results = results
            )
        }
    }
}