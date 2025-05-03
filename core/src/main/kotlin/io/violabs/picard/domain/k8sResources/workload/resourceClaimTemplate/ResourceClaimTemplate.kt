package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim

data class ResourceClaimTemplate(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
) : K8sResource<ResourceClaimTemplate.Version> {
    interface Version : APIVersion

    data class Spec(
        val spec: ResourceClaim.Spec,
        val metadata: ObjectMetadata? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var spec: ResourceClaim.Spec? = null
            private var metadata: ObjectMetadata? = null

            fun resourceClaimSpec(scope: ResourceClaim.Spec.Builder.() -> Unit) {
                spec = ResourceClaim.Spec.Builder().apply(scope).build()
            }

            fun metadata(scope: ObjectMetadata.Builder.() -> Unit) {
                metadata = ObjectMetadata.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    spec = vRequireNotNull(this::spec),
                    metadata = metadata
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<ResourceClaimTemplate, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): ResourceClaimTemplate {
            return ResourceClaimTemplate(
                spec = vRequireNotNull(this::spec),
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ResourceClaimTemplate, Builder>(Builder()) {
        fun resourceClaimTemplate(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}