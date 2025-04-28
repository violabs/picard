package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ValidatingAdmissionPolicyBindingList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<ValidatingAdmissionPolicyBinding>,
    override val metadata: ListMeta? = null
) : K8sListResource<ValidatingAdmissionPolicyBindingList.Version, ValidatingAdmissionPolicyBinding> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        ValidatingAdmissionPolicyBinding,
        ValidatingAdmissionPolicyBinding.Builder,
        ValidatingAdmissionPolicyBinding.Group,
        ValidatingAdmissionPolicyBindingList
        >(ValidatingAdmissionPolicyBinding.Group()) {

        override fun build(): ValidatingAdmissionPolicyBindingList {
            return ValidatingAdmissionPolicyBindingList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
