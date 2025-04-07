package io.violabs.picard.domain.k8sResources.authentication.tokenReview

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.UserInfo
import io.violabs.picard.domain.BaseSpec

class TokenReview(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<TokenReview.Version> {
    interface Version : APIVersion

    data class Spec(
        val audiences: List<String>? = null,
        val token: String? = null
    ) : BaseSpec

    data class Status(
        val audiences: List<String>? = null,
        val authenticated: Boolean? = null,
        val error: String? = null,
        val user: UserInfo? = null
    ) : BaseSpec
}