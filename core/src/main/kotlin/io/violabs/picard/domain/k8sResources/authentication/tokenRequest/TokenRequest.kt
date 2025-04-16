package io.violabs.picard.domain.k8sResources.authentication.tokenRequest

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.BoundObjectReference
import java.time.LocalDateTime

data class TokenRequest(
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
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
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
        class Builder : DSLBuilder<Status> {
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

    class Builder : ResourceSpecStatusDSLBuilder<TokenRequest, Spec, Spec.Builder, Status, Status.Builder>(
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