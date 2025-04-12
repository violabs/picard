package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.domain.DeviceSelector
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.OpaqueDeviceConfiguration
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.k8sResources.workload.NodeSelector

class ResourceClaim(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<ResourceClaim.Version> {
    interface Version : APIVersion

    data class Spec(
        val controller: String? = null,
        val devices: DeviceClaim? = null
    ) : BaseSpec

    data class Status(
        val allocation: AllocationResult? = null,
        val deallocationRequested: Boolean? = null,
        val reservedFor: List<ConsumerReference>? = null
    ) : BaseStatus

    data class AllocationResult(
        val controller: String? = null,
        val devices: DeviceAllocationResult? = null,
        val nodeSelector: NodeSelector? = null
    )

    data class DeviceAllocationResult(
        val config: List<DeviceAllocationConfiguration>? = null,
        val results: List<DeviceRequest.AllocationResult>? = null
    )

    data class DeviceAllocationConfiguration(
        val source: String,
        val opaque: OpaqueDeviceConfiguration? = null,
        val request: List<String>? = null
    )

    data class DeviceClaim(
        val config: Configuration? = null,
        val constraints: List<DeviceConstraint>? = null,
        val requests: List<DeviceRequest>? = null
    ) {
        data class Configuration(
            val opaque: OpaqueDeviceConfiguration? = null,
            val requests: List<String>? = null
        )
    }

    data class DeviceConstraint(
        val matchAttribute: String? = null,
        val requests: List<String>? = null
    )

    data class DeviceRequest(
        val deviceClassName: String,
        val name: String,
        val adminAccess: Boolean? = null,
        val allocationMode: AllocationMode? = null,
        val count: Long? = null,
        val selectors: List<DeviceSelector>? = null
    ) {
        data class AllocationResult(
            val device: String,
            val driver: String,
            val pool: String,
            val request: String
        )

        enum class AllocationMode {
            ExactCount,
            All
        }
    }

    data class ConsumerReference(
        val name: String,
        val resource: String,
        val uid: String,
        val apiGroup: String? = null
    )
}