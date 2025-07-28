package io.violabs.picard.v2.resources.policy.admission.mutating

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/extend-resources/mutating-admission-policy-v1alpha1/
 *
 * MutatingAdmissionPolicy describes the definition of an admission mutation policy that mutates the object
 * coming into admission chain.
 *
 * MutatingAdmissionPolicy v1alpha1
 * MutatingAdmissionPolicy describes the definition of an admission mutation policy that mutates the object 
 * coming into admission chain.
 * 
 * apiVersion: admissionregistration.k8s.io/v1alpha1
 *
 * import "k8s.io/api/admissionregistration/v1alpha1"
 */
@GeneratedDsl
data class MutatingAdmissionPolicyV2(
    @DefaultValue(
        "KAPIVersion.AdmissionRegistrationV1Alpha1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1Alpha1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of the MutatingAdmissionPolicy.
     */
    val spec: MutatingAdmissionPolicySpec? = null
) : PolicyResource<MutatingAdmissionPolicyV2.Version, ObjectMeta> {
    interface Version : APIVersion
}