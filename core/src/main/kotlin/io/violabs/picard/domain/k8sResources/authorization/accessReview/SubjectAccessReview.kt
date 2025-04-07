package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.NonResourceAttributes
import io.violabs.picard.domain.k8sResources.authorization.ResourceAttributes
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class SubjectAccessReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<SubjectAccessReview.Version> {
    interface Version : APIVersion


    data class Spec(
        val extra: Map<String, List<String>>? = null,
        val groups: List<String>? = null,
        val nonResourceAttributes: NonResourceAttributes? = null,
        val resourceAttributes: ResourceAttributes? = null,
        val uid: String? = null,
        val user: String? = null
    ) : BaseSpec

    data class Status(
        val allowed: Boolean,
        val denied: Boolean? = null,
        val evaluationError: String? = null,
        val reason: String? = null
    ) : BaseSpec
}