package io.violabs.picard.domain.k8sResources.workload.resourceClaimTemplate

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.resourceClaim.ResourceClaim
import io.violabs.picard.domain.manifest.WorkloadResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.workload.resource.claim.template.ResourceClaimTemplateV2"))
data class ResourceClaimTemplate(
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta1,
    val spec: Spec,
    override val metadata: ObjectMetadata? = null,
) : WorkloadResource<ResourceClaimTemplate.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val spec: ResourceClaim.Spec,
        val metadata: ObjectMetadata? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
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

    class Builder : ResourceSpecDslBuilder<ResourceClaimTemplate, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): ResourceClaimTemplate {
            return ResourceClaimTemplate(
                spec = vRequireNotNull(this::spec),
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ResourceClaimTemplate, Builder>(Builder()) {
        fun resourceClaimTemplateItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}