package io.violabs.picard.v2.resources.authentication.token.request

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.AuthenticationListResource

@GeneratedDsl
data class TokenRequestList(
    @DefaultValue(
        "KAPIVersion.AuthenticationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val items: List<TokenRequest>,
    override val metadata: ListMeta? = null
) : AuthenticationListResource<TokenRequestList.Version, TokenRequest> {
    interface Version : APIVersion
}
