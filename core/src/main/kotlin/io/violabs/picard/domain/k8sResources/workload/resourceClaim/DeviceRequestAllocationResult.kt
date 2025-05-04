package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class DeviceRequestAllocationResult(
    val device: String,
    val driver: String,
    val pool: String,
    val request: String
) {
    class Builder : DSLBuilder<DeviceRequestAllocationResult> {
        var device: String? = null
        var driver: String? = null
        var pool: String? = null
        var request: String? = null

        override fun build(): DeviceRequestAllocationResult {
            return DeviceRequestAllocationResult(
                device = vRequireNotNull(this::device),
                driver = vRequireNotNull(this::driver),
                pool = vRequireNotNull(this::pool),
                request = vRequireNotNull(this::request)
            )
        }
    }

    class Group : BuilderGroup<DeviceRequestAllocationResult, Builder>(Builder()) {
        fun results(): List<DeviceRequestAllocationResult>? = items()

        fun result(block: Builder.() -> Unit) {
            add(block)
        }
    }
}