package io.violabs.picard.domain.k8sResources.authentication.tokenRequest

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationListResource

data class TokenRequestList(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<TokenRequest>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<TokenRequestList.Version, TokenRequest> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        TokenRequest,
        TokenRequest.Builder,
        TokenRequest.Group,
        TokenRequestList
        >(TokenRequest.Group()) {

        override fun build(): TokenRequestList {
            return TokenRequestList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
