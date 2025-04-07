package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ValidatingAdmissionPolicyList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<ValidatingAdmissionPolicy>,
    override val metadata: ListMeta? = null
) : K8sListResource<ValidatingAdmissionPolicyList.Version, ValidatingAdmissionPolicy> {
    interface Version : APIVersion
}
