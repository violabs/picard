package io.violabs.picard.domain.k8sResources.authentication.tokenReview

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class TokenReviewList(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<TokenReview>,
    override val metadata: ListMeta? = null
) : K8sListResource<TokenReviewList.Version, TokenReview> {
    interface Version : APIVersion
}
