package io.violabs.picard.domain.k8sResources.workload.resourceSlice

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.Device
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector
import io.violabs.picard.domain.manifest.WorkloadResource

data class ResourceSlice(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
) : WorkloadResource<ResourceSlice.Version> {
    interface Version : APIVersion

    data class Spec(
        val driver: String,
        val pool: ResourcePool,
        val allNodes: Boolean? = null,
        val devices: List<Device>? = null,
        val nodeName: String? = null,
        val nodeSelector: NodeSelector? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            var driver: String? = null
            private var pool: ResourcePool? = null
            private var allNodes: Boolean? = null
            private var devices: List<Device>? = null
            var nodeName: String? = null
            private var nodeSelector: NodeSelector? = null

            fun pool(scope: ResourcePool.Builder.() -> Unit) {
                pool = ResourcePool.Builder().apply(scope).build()
            }

            fun allNodes(value: Boolean = true) {
                allNodes = value
            }

            fun devices(block: Device.Group.() -> Unit) {
                devices = Device.Group().apply(block).devices()
            }

            fun nodeSelector(scope: NodeSelector.Builder.() -> Unit) {
                nodeSelector = NodeSelector.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    driver = vRequireNotNull(this::driver),
                    pool = vRequireNotNull(this::pool),
                    allNodes = allNodes,
                    devices = devices,
                    nodeName = nodeName,
                    nodeSelector = nodeSelector
                )
            }
        }
    }

    class Builder : ResourceSpecDslBuilder<ResourceSlice, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): ResourceSlice {
            return ResourceSlice(
                spec = vRequireNotNull(this::spec),
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ResourceSlice, Builder>(Builder()) {
        fun resourceSliceItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}

