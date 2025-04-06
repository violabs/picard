package io.violabs.picard.domain.k8sResources.authorization

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class SelfSubjectRulesReviewList(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val items: List<SelfSubjectRulesReview>,
    override val metadata: ListMeta? = null
) : K8sListResource<SelfSubjectRulesReviewList.Version, SelfSubjectRulesReview> {
    interface Version : APIVersion
}
