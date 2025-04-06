package io.violabs.picard.domain.k8sResources.policy

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class ValidatingAdmissionPolicy(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<ValidatingAdmissionPolicy.Version> {

    interface Version : APIVersion
}