package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class LocalSubjectAccessReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: SubjectAccessReview.Spec,
    val status: SubjectAccessReview.Status? = null
) : K8sResource<LocalSubjectAccessReview.Version> {
    interface Version : APIVersion

    class Builder : ResourceSpecStatusDSLBuilder<
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