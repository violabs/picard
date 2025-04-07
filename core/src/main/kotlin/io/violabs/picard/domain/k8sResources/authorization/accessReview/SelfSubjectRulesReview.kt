package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.NonResourceRule
import io.violabs.picard.domain.k8sResources.authorization.ResourceRule
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class SelfSubjectRulesReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<SelfSubjectRulesReview.Version> {
    interface Version : APIVersion

    data class Spec(val namespace: String) : BaseSpec
    data class Status(
        val incomplete: Boolean,
        val nonResourceRule: List<NonResourceRule>,
        val resourceRules: List<ResourceRule>,
        val evaluationError: String? = null
    ) : BaseSpec
}