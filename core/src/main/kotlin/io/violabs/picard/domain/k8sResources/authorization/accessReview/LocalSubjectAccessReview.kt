package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthorizationResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.authorization.review.access.subject.local.LocalSubjectAccessReviewV2"))
data class LocalSubjectAccessReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: SubjectAccessReview.Spec,
    val status: SubjectAccessReview.Status? = null
) : AuthorizationResource<LocalSubjectAccessReview.Version, ObjectMetadata> {
    interface Version : APIVersion

    class Builder : ResourceSpecStatusDslBuilder<
        LocalSubjectAccessReview,
        SubjectAccessReview.Spec,
        SubjectAccessReview.Spec.Builder,
        SubjectAccessReview.Status,
        SubjectAccessReview.Status.Builder
        >(SubjectAccessReview.Spec.Builder(), SubjectAccessReview.Status.Builder()) {
        override fun build(): LocalSubjectAccessReview {
            return LocalSubjectAccessReview(
                spec = vRequireNotNull(this::spec),
                status = status,
                metadata = metadata
            )
        }
    }
}