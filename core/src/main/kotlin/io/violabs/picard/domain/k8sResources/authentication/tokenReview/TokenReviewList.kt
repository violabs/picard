package io.violabs.picard.domain.k8sResources.authentication.tokenReview

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationListResource

data class TokenReviewList(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<TokenReview>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<TokenReviewList.Version, TokenReview> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        TokenReview,
        TokenReview.Builder,
        TokenReview.Group,
        TokenReviewList
        >(TokenReview.Group()) {
        override fun build(): TokenReviewList {
            return TokenReviewList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
