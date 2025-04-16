package io.violabs.picard.domain.k8sResources.authentication.tokenRequest

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class TokenRequestList(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<TokenRequest>,
    override val metadata: ListMeta? = null
) : K8sListResource<TokenRequestList.Version, TokenRequest> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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
