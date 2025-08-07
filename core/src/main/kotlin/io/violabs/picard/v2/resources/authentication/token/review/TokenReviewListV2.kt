package io.violabs.picard.v2.resources.authentication.token.review

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class TokenReviewListV2(
    @DefaultValue(
        "KAPIVersion.AuthenticationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<TokenReviewV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<TokenReviewListV2.Version, TokenReviewV2> {
    interface Version : APIVersion
}
