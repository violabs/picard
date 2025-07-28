package io.violabs.picard.v2.resources.policy.admission.mutating

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/extend-resources/mutating-admission-policy-binding-v1alpha1/
 *
 * MutatingAdmissionPolicyBinding binds the MutatingAdmissionPolicy with parametrized resources.
 * MutatingAdmissionPolicyBinding and the optional parameter resource together define how cluster 
 * administrators configure policies for clusters.
 *
 * For a given admission request, each binding will cause its policy to be evaluated N times, where N is 1 for 
 * policies/bindings that don't use params, otherwise N is the number of parameters selected by the binding. 
 * Each evaluation is constrained by a runtime cost budget.
 *
 * Adding/removing policies, bindings, or params can not affect whether a given (policy, binding, param) 
 * combination is within its own CEL budget.
 *
 * apiVersion: admissionregistration.k8s.io/v1alpha1
 *
 * import "k8s.io/api/admissionregistration/v1alpha1"
 */
@GeneratedDsl
data class MutatingAdmissionPolicyBindingV2(
    @DefaultValue(
        "KAPIVersion.AdmissionRegistrationV1Alpha1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1Alpha1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of the MutatingAdmissionPolicyBinding.
     */
    val spec: MutatingAdmissionPolicyBindingSpec? = null
) : PolicyResource<MutatingAdmissionPolicyBindingV2.Version, ObjectMeta> {
    interface Version : APIVersion
}