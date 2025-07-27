package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.NonResourceAttributes
import io.violabs.picard.domain.k8sResources.authorization.ResourceAttributes
import io.violabs.picard.domain.manifest.AuthorizationResource

@Deprecated("Use v2", ReplaceWith("io.violabs.picard.v2.resources.authorization.review.access.subject.SubjectAccessReviewV2"))
data class SubjectAccessReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : AuthorizationResource<SubjectAccessReview.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val extra: Map<String, List<String>>? = null,
        val groups: List<String>? = null,
        val nonResourceAttributes: NonResourceAttributes? = null,
        val resourceAttributes: ResourceAttributes? = null,
        val uid: String? = null,
        val user: String? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var extra: Map<String, List<String>>? = null
            private var groups: List<String>? = null
            private var nonResourceAttributes: NonResourceAttributes? = null
            private var resourceAttributes: ResourceAttributes? = null
            var uid: String? = null
            var user: String? = null

            fun extra(vararg entries: Pair<String, List<String>>) {
                this.extra = entries.toMap()
            }

            fun groups(vararg groups: String) {
                this.groups = groups.toList()
            }

            fun nonResourceAttributes(scope: NonResourceAttributes.Builder.() -> Unit) {
                nonResourceAttributes = NonResourceAttributes.Builder().apply(scope).build()
            }

            fun resourceAttributes(scope: ResourceAttributes.Builder.() -> Unit) {
                resourceAttributes = ResourceAttributes.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    extra = extra,
                    groups = groups,
                    nonResourceAttributes = nonResourceAttributes,
                    resourceAttributes = resourceAttributes,
                    uid = uid,
                    user = user
                )
            }
        }
    }

    data class Status(
        val allowed: Boolean,
        val denied: Boolean? = null,
        val evaluationError: String? = null,
        val reason: String? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var allowed: Boolean? = null
            private var denied: Boolean? = null
            var evaluationError: String? = null
            var reason: String? = null

            fun allowed(value: Boolean = true) {
                this.allowed = value
            }

            fun denied(value: Boolean = true) {
                this.denied = value
            }

            override fun build(): Status {
                return Status(
                    allowed = vRequireNotNull(this::allowed),
                    denied = denied,
                    evaluationError = evaluationError,
                    reason = reason
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        SubjectAccessReview,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder
        >(Spec.Builder(), Status.Builder()) {
        override fun build(): SubjectAccessReview {
            return SubjectAccessReview(
                spec = vRequireNotNull(this::spec),
                status = status,
                metadata = metadata
            )
        }
    }
}