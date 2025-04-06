package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class SelfSubjectRulesReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null
) : K8sResource<SelfSubjectRulesReview.Version> {

    interface Version : APIVersion
}