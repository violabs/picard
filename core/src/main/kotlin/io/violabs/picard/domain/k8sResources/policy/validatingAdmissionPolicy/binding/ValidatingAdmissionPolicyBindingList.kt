package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ValidatingAdmissionPolicyBindingList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<ValidatingAdmissionPolicyBinding>,
    override val metadata: ListMeta? = null
) : K8sListResource<ValidatingAdmissionPolicyBindingList.Version, ValidatingAdmissionPolicyBinding> {
    interface Version : APIVersion
}
