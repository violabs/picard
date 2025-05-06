package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.domain.DeviceSelector

data class DeviceRequest(
    val deviceClassName: String,
    val name: String,
    val adminAccess: Boolean? = null,
    val allocationMode: AllocationMode? = null,
    val count: Long? = null,
    val selectors: List<DeviceSelector>? = null
) {
    class Builder : DSLBuilder<DeviceRequest> {
        var deviceClassName: String? = null
        var name: String? = null
        private var adminAccess: Boolean? = null
        var allocationMode: AllocationMode? = null
        var count: Long? = null
        private var selectors: List<DeviceSelector>? = null

        fun adminAccess(value: Boolean = true) {
            this.adminAccess = value
        }

        fun selectors(scope: DeviceSelector.Group.() -> Unit) {
            this.selectors = DeviceSelector.Group().apply(scope).selectors()
        }

        override fun build(): DeviceRequest {
            return DeviceRequest(
                deviceClassName = vRequireNotNull(this::deviceClassName),
                name = vRequireNotNull(this::name),
                adminAccess = adminAccess,
                allocationMode = allocationMode,
                count = count,
                selectors = selectors
            )
        }
    }

    class Group : BuilderGroup<DeviceRequest, Builder>(Builder()) {
        fun requests(): List<DeviceRequest>? = items()

        fun addDeviceRequest(block: Builder.() -> Unit) {
            add(block)
        }
    }
}