package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.NonResourceAttributes
import io.violabs.picard.domain.k8sResources.authorization.ResourceAttributes
import io.violabs.picard.domain.BaseSpec

class SelfSubjectAccessReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: SubjectAccessReview.Status? = null
) : K8sResource<SelfSubjectAccessReview.Version> {
    interface Version : APIVersion

    data class Spec(
        val nonResourceAttributes: NonResourceAttributes? = null,
        val resourceAttributes: ResourceAttributes? = null
    ) : BaseSpec
}