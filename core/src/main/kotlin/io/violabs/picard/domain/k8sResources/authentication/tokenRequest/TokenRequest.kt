package io.violabs.picard.domain.k8sResources.authentication.tokenRequest

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.BoundObjectReference
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus
import java.time.LocalDateTime

class TokenRequest(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<TokenRequest.Version> {
    interface Version : APIVersion

    data class Spec(
        val audiences: List<String>,
        val boundObjectRef: BoundObjectReference? = null,
        val expirationSeconds: Long? = null
    ) : BaseSpec

    data class Status(
        val expirationTimestamp: LocalDateTime,
        val token: String
    ) : BaseStatus
}