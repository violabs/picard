package io.violabs.picard.domain.k8sResources.authorization.accessReview

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecStatusDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.authorization.NonResourceRule
import io.violabs.picard.domain.k8sResources.authorization.ResourceRule
import io.violabs.picard.domain.manifest.AuthorizationResource

data class SelfSubjectRulesReview(
    override val apiVersion: Version = KAPIVersion.AuthorizationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec,
    val status: Status? = null
) : AuthorizationResource<SelfSubjectRulesReview.Version> {
    interface Version : APIVersion

    data class Spec(val namespace: String) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            var namespace: String? = null
            override fun build(): Spec {
                return Spec(vRequireNotNull(this::namespace))
            }
        }
    }

    data class Status(
        val incomplete: Boolean,
        val nonResourceRules: List<NonResourceRule>,
        val resourceRules: List<ResourceRule>,
        val evaluationError: String? = null
    ) : BaseStatus {
        class Builder : DslBuilder<Status> {
            private var incomplete: Boolean? = null
            private var nonResourceRules: List<NonResourceRule>? = null
            private var resourceRules: List<ResourceRule>? = null
            var evaluationError: String? = null

            fun incomplete(value: Boolean = true) {
                incomplete = value
            }

            fun nonResourceRules(scope: NonResourceRule.Group.() -> Unit) {
                nonResourceRules = NonResourceRule.Group().apply(scope).rules()
            }

            fun resourceRules(scope: ResourceRule.Group.() -> Unit) {
                resourceRules = ResourceRule.Group().apply(scope).rules()
            }

            override fun build(): Status {
                return Status(
                    incomplete = vRequireNotNull(this::incomplete),
                    nonResourceRules = vRequireNotEmpty(this::nonResourceRules),
                    resourceRules = vRequireNotEmpty(this::resourceRules),
                    evaluationError = evaluationError
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDslBuilder<
        SelfSubjectRulesReview,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder
        >(Spec.Builder(), Status.Builder()) {
        override fun build(): SelfSubjectRulesReview {
            return SelfSubjectRulesReview(
                spec = vRequireNotNull(this::spec),
                status = status,
                metadata = metadata
            )
        }
    }
}