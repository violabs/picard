package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.DSLBuilder

data class DeviceClaim(
    val config: DeviceClaimConfiguration? = null,
    val constraints: List<DeviceConstraint>? = null,
    val requests: List<DeviceRequest>? = null
) {
    class Builder : DSLBuilder<DeviceClaim> {
        private var config: DeviceClaimConfiguration? = null
        private var constraints: List<DeviceConstraint>? = null
        private var requests: List<DeviceRequest>? = null

        fun config(block: DeviceClaimConfiguration.Builder.() -> Unit) {
            config = DeviceClaimConfiguration.Builder().apply(block).build()
        }

        fun constraints(scope: DeviceConstraint.Group.() -> Unit) {
            constraints = DeviceConstraint.Group().apply(scope).constraints()
        }

        fun requests(scope: DeviceRequest.Group.() -> Unit) {
            requests = DeviceRequest.Group().apply(scope).requests()
        }

        override fun build(): DeviceClaim {
            return DeviceClaim(
                config = config,
                constraints = constraints,
                requests = requests
            )
        }
    }
}