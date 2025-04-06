package io.violabs.picard.domain.k8sResources.authentication

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class SelfSubjectReview(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<SelfSubjectReview.Version> {

    interface Version : APIVersion
}