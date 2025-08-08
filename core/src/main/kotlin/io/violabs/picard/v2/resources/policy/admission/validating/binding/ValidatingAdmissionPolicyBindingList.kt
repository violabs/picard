package io.violabs.picard.v2.resources.policy.admission.validating.binding

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.PolicyListResource

@GeneratedDsl
data class ValidatingAdmissionPolicyBindingList(
    @DefaultValue(
        "KAPIVersion.AdmissionRegistrationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<ValidatingAdmissionPolicyBinding>,
    override val metadata: ListMeta? = null
) : PolicyListResource<ValidatingAdmissionPolicyBindingList.Version, ValidatingAdmissionPolicyBinding> {
    interface Version : APIVersion
}
