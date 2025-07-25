package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.NonResourceAttributes
import io.violabs.picard.domain.k8sResources.authorization.ResourceAttributes
import io.violabs.picard.domain.manifest.AuthorizationResource

data class SelfSubjectAccessReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: SubjectAccessReview.Status? = null
) : AuthorizationResource<SelfSubjectAccessReview.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val nonResourceAttributes: NonResourceAttributes? = null,
        val resourceAttributes: ResourceAttributes? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var nonResourceAttributes: NonResourceAttributes? = null
            private var resourceAttributes: ResourceAttributes? = null

            fun nonResourceAttributes(scope: NonResourceAttributes.Builder.() -> Unit) {
                nonResourceAttributes = NonResourceAttributes.Builder().apply(scope).build()
            }

            fun resourceAttributes(scope: ResourceAttributes.Builder.() -> Unit) {
                resourceAttributes = ResourceAttributes.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(nonResourceAttributes, resourceAttributes)
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        SelfSubjectAccessReview,
        Spec,
        Spec.Builder,
        SubjectAccessReview.Status,
        SubjectAccessReview.Status.Builder
        >(Spec.Builder(), SubjectAccessReview.Status.Builder()) {
        override fun build(): SelfSubjectAccessReview {
            return SelfSubjectAccessReview(
                spec = vRequireNotNull(this::spec),
                metadata = metadata,
                status = status
            )
        }
    }
}