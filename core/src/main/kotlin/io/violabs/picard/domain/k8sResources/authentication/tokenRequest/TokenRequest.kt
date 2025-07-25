package io.violabs.picard.domain.k8sResources.authentication.tokenRequest

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.BoundObjectReference
import io.violabs.picard.domain.manifest.AuthenticationResource
import java.time.LocalDateTime

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.authentication.token.request.TokenRequestV2"))
data class TokenRequest(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : AuthenticationResource<TokenRequest.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val audiences: List<String>,
        val boundObjectRef: BoundObjectReference? = null,
        val expirationSeconds: Long? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var audiences: List<String>? = null
            private var boundObjectRef: BoundObjectReference? = null
            var expirationSeconds: Long? = null

            fun audiences(vararg audiences: String) {
                this.audiences = audiences.toList()
            }

            fun boundObjectRef(scope: BoundObjectReference.Builder.() -> Unit) {
                boundObjectRef = BoundObjectReference.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    audiences = vRequireNotEmpty(this::audiences),
                    boundObjectRef = boundObjectRef,
                    expirationSeconds = expirationSeconds
                )
            }
        }
    }

    data class Status(
        val expirationTimestamp: LocalDateTime,
        val token: String
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            var expirationTimestamp: LocalDateTime? = null
            var token: String? = null

            override fun build(): Status {
                return Status(
                    expirationTimestamp = vRequireNotNull(this::expirationTimestamp),
                    token = vRequireNotNull(this::token)
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<TokenRequest, Spec, Spec.Builder, Status, Status.Builder>(
        Spec.Builder(),
        Status.Builder()
    ) {
        override fun build(): TokenRequest {
            return TokenRequest(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<TokenRequest, Builder>(Builder()) {
        fun request(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}