package io.violabs.picard.domain.k8sResources.authentication

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class SelfSubjectReviewList(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<SelfSubjectReview>,
    override val metadata: ListMeta? = null
) : K8sListResource<SelfSubjectReviewList.Version, SelfSubjectReview> {
    interface Version : APIVersion
}
