package io.violabs.picard.v2.resources.authentication.token.request

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/token-request-v1/
 *
 * TokenRequest requests a token for a given service account.
 *
 * apiVersion: authentication.k8s.io/v1
 *
 * import "k8s.io/api/authentication/v1"
 */
@GeneratedDsl(withListGroup = true)
data class TokenRequest(
    @DefaultValue(
        "KAPIVersion.AuthenticationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    /**
     * Standard object's metadata.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Spec holds information about the request being evaluated
     */
    val spec: TokenRequestSpec,
    /**
     * Status is filled in by the server and indicates whether the token can be authenticated.
     */
    val status: TokenRequestStatus? = null
) : AuthenticationResource<TokenRequest.Version, ObjectMeta> {
    interface Version : APIVersion
}
