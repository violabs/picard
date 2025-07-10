package io.violabs.picard.domain.k8sResources.storage.csi.csiNode

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.StorageResource

data class CSINode(
    override val apiVersion: Version = KAPIVersion.StorageV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : StorageResource<CSINode.Version> {
    interface Version : APIVersion

    data class Spec(
        val drivers: List<CSINodeDriver>
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var drivers: List<CSINodeDriver>? = null

            fun drivers(scope: CSINodeDriver.Group.() -> Unit) {
                drivers = CSINodeDriver.Group().apply(scope).drivers()
            }

            override fun build(): Spec {
                return Spec(
                    drivers = vRequireNotEmpty(this::drivers)
                )
            }
        }
    }

    class Builder : ResourceSpecDslBuilder<CSINode, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): CSINode {
            return CSINode(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<CSINode, Builder>(Builder()) {
        fun csiNodeItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}