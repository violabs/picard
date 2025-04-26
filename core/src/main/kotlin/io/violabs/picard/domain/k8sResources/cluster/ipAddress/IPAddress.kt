package io.violabs.picard.domain.k8sResources.cluster.ipAddress

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.ResourceSpecDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class IPAddress(
    override val apiVersion: Version = KAPIVersion.NetworkingV1Beta1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<IPAddress.Version> {
    interface Version : APIVersion

    data class Spec(val parentRef: ParentReference) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var parentRef: ParentReference? = null

            fun parentRef(scope: ParentReference.Builder.() -> Unit) {
                parentRef = ParentReference.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    parentRef = vRequireNotNull(this::parentRef)
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<IPAddress, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): IPAddress {
            return IPAddress(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<IPAddress, Builder>(Builder()) {
        fun address(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}