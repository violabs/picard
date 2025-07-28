package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.policy.MatchResources
import io.violabs.picard.domain.k8sResources.policy.ParamRef
import io.violabs.picard.domain.manifest.PolicyResource

@Deprecated("Use ValidatingAdmissionPolicyBindingV2", ReplaceWith("ValidatingAdmissionPolicyBindingV2", "io.violabs.picard.v2.resources.policy.admission.validating.ValidatingAdmissionPolicyBindingV2"))
data class ValidatingAdmissionPolicyBinding(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : PolicyResource<ValidatingAdmissionPolicyBinding.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val matchResources: MatchResources? = null,
        val paramRef: ParamRef? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var matchResources: MatchResources? = null
            private var paramRef: ParamRef? = null

            fun matchResources(scope: MatchResources.Builder.() -> Unit) {
                matchResources = MatchResources.Builder().apply(scope).build()
            }

            fun paramRef(scope: ParamRef.Builder.() -> Unit) {
                paramRef = ParamRef.Builder().apply(scope).build()
            }

            override fun build(): Spec {
                return Spec(
                    matchResources = matchResources,
                    paramRef = paramRef
                )
            }
        }
    }

    class Builder : ResourceSpecDslBuilder<ValidatingAdmissionPolicyBinding, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): ValidatingAdmissionPolicyBinding {
            return ValidatingAdmissionPolicyBinding(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ValidatingAdmissionPolicyBinding, Builder>(Builder()) {
        fun validatingAdmissionPolicyBindingItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}