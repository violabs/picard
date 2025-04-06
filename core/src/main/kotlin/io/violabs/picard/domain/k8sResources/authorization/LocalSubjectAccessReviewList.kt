package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class LocalSubjectAccessReviewList(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val items: List<LocalSubjectAccessReview>,
    override val metadata: ListMeta? = null
) : K8sListResource<LocalSubjectAccessReviewList.Version, LocalSubjectAccessReview> {
    interface Version : APIVersion
}
