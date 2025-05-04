package io.violabs.picard.domain.k8sResources.extend.deviceClass

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.DeviceSelector
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.nodeSelector.NodeSelector
import io.violabs.picard.domain.manifest.ExtendResource

data class DeviceClass(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null
) : ExtendResource<DeviceClass.Version> {
    interface Version : APIVersion

    data class Spec(
        val config: List<DeviceClassConfiguration>? = null,
        val selectors: List<DeviceSelector>? = null,
        val suitableNodes: NodeSelector? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var config: List<DeviceClassConfiguration>? = null
            private var selectors: List<DeviceSelector>? = null
            private var suitableNodes: NodeSelector? = null

            fun config(scope: DeviceClassConfiguration.Group.() -> Unit) {
                this.config = DeviceClassConfiguration.Group().apply(scope).configs()
            }

            fun selectors(scope: DeviceSelector.Group.() -> Unit) {
                this.selectors = DeviceSelector.Group().apply(scope).selectors()
            }

            fun suitableNodes(scope: NodeSelector.Builder.() -> Unit) {
                this.suitableNodes = NodeSelector.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    config = config,
                    selectors = selectors,
                    suitableNodes = suitableNodes
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<DeviceClass, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): DeviceClass {
            return DeviceClass(
                metadata = metadata,
                spec = vRequireNotNull(this::spec)
            )
        }
    }

    class Group : K8sListResource.ItemGroup<DeviceClass, Builder>(Builder()) {
        fun deviceClassItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}