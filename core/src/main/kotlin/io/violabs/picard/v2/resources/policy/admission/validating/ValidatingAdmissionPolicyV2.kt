package io.violabs.picard.v2.resources.policy.admission.validating

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ValidatingAdmissionPolicy describes the definition of an admission validation policy 
 * that accepts or rejects an object without changing it.
 *
 * apiVersion: admissionregistration.k8s.io/v1
 * 
 * import "k8s.io/api/admissionregistration/v1"
 */
@GeneratedDsl
data class ValidatingAdmissionPolicyV2(
    @DefaultValue(
        "KAPIVersion.AdmissionRegistrationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMeta? = null,
    /**
     * spec is the specification of the desired behavior of the ValidatingAdmissionPolicy.
     */
    val spec: ValidatingAdmissionPolicySpec? = null,
    /**
     * status represents the status of a ValidatingAdmissionPolicy, including warnings 
     * that are useful to determine if the policy behaves in the expected way. 
     * Populated by the system. Read-only.
     */
    val status: ValidatingAdmissionPolicyStatus? = null
) : PolicyResource<ValidatingAdmissionPolicyV2.Version, ObjectMeta> {
    interface Version : APIVersion
}