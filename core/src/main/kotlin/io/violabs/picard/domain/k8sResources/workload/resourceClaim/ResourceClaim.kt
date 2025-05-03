package io.violabs.picard.domain.k8sResources.workload.resourceClaim

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.ResourceSpecStatusDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ResourceClaim(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
    val status: Status? = null
) : K8sResource<ResourceClaim.Version> {
    interface Version : APIVersion

    data class Spec(
        val controller: String? = null,
        val devices: DeviceClaim? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var controller: String? = null
            private var devices: DeviceClaim? = null

            fun devices(block: DeviceClaim.Builder.() -> Unit) {
                devices = DeviceClaim.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    controller = controller,
                    devices = devices
                )
            }
        }
    }

    data class Status(
        val allocation: AllocationResult? = null,
        val deallocationRequested: Boolean? = null,
        val reservedFor: List<ConsumerReference>? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var allocation: AllocationResult? = null
            private var deallocationRequested: Boolean? = null
            private var reservedFor: List<ConsumerReference>? = null

            fun allocation(block: AllocationResult.Builder.() -> Unit) {
                allocation = AllocationResult.Builder().apply(block).build()
            }

            fun deallocationRequested(value: Boolean = true) {
                deallocationRequested = value
            }

            fun reservedFor(scope: ConsumerReference.Group.() -> Unit) {
                reservedFor = ConsumerReference.Group().apply(scope).references()
            }

            override fun build(): Status {
                return Status(
                    allocation = allocation,
                    deallocationRequested = deallocationRequested,
                    reservedFor = reservedFor
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        ResourceClaim,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {
        override fun build(): ResourceClaim {
            return ResourceClaim(
                spec = vRequireNotNull(this::spec),
                metadata = metadata,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ResourceClaim, Builder>(Builder()) {
        fun claim(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}