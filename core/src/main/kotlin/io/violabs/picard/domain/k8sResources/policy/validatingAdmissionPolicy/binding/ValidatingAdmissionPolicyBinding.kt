package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.policy.MatchResources
import io.violabs.picard.domain.k8sResources.policy.ParamRef
import io.violabs.picard.domain.k8sResources.workload.BaseSpec

class ValidatingAdmissionPolicyBinding(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<ValidatingAdmissionPolicyBinding.Version> {
    interface Version : APIVersion

    data class Spec(
        val matchResources: MatchResources? = null,
        val paramRef: ParamRef? = null
    ) : BaseSpec
}