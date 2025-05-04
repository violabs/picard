package io.violabs.picard.domain.k8sResources.authentication.tokenReview

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authentication.UserInfo

data class TokenReview(
    override val apiVersion: Version = KAPIVersion.AuthenticationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : K8sResource<TokenReview.Version> {
    interface Version : APIVersion

    data class Spec(
        val audiences: List<String>? = null,
        val token: String? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var audiences: List<String>? = null
            var token: String? = null

            fun audiences(vararg audiences: String) {
                this.audiences = audiences.toList()
            }

            override fun build(): Spec {
                return Spec(
                    audiences = audiences,
                    token = token
                )
            }
        }
    }

    data class Status(
        val audiences: List<String>? = null,
        val authenticated: Boolean? = null,
        val error: String? = null,
        val user: UserInfo? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var audiences: List<String>? = null
            private var authenticated: Boolean? = null
            var error: String? = null
            private var user: UserInfo? = null

            fun audiences(vararg audiences: String) {
                this.audiences = audiences.toList()
            }

            fun authenticated(authenticated: Boolean = true) {
                this.authenticated = authenticated
            }

            fun user(scope: UserInfo.Builder.() -> Unit) {
                this.user = UserInfo.Builder().apply(scope).build()
            }

            override fun build(): Status {
                return Status(
                    audiences = audiences,
                    authenticated = authenticated,
                    error = error,
                    user = user
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        TokenReview,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder
        >(Spec.Builder(), Status.Builder()) {
        override fun build(): TokenReview {
            return TokenReview(
                spec = vRequireNotNull(this::spec),
                status = status,
                metadata = metadata
            )
        }
    }

    class Group : K8sListResource.ItemGroup<TokenReview, Builder>(Builder()) {
        fun review(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}