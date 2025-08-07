package io.violabs.picard.v2.resources.authentication.token.review

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.AuthenticationResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/authentication-resources/token-review-v1/
 *
 * TokenReview attempts to authenticate a token to a known user. Note: TokenReview
 * requests may be cached by the webhook token authenticator plugin in the kube-apiserver.
 *
 * apiVersion: authentication.k8s.io/v1
 *
 * import "k8s.io/api/authentication/v1"
 */
@GeneratedDsl(withListGroup = true)
data class TokenReviewV2(
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
     * Spec holds information about the request being evaluated. Required.
     */
    val spec: TokenReviewSpec,
    /**
     * Status is filled in by the server and indicates whether the request can be authenticated.
     */
    val status: TokenReviewStatus? = null
) : AuthenticationResource<TokenReviewV2.Version, ObjectMeta> {
    interface Version : APIVersion
}

