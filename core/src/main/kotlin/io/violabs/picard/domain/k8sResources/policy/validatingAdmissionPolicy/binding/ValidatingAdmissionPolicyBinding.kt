package io.violabs.picard.domain.k8sResources.policy.validatingAdmissionPolicy.binding

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.policy.MatchResources
import io.violabs.picard.domain.k8sResources.policy.ParamRef
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class ValidatingAdmissionPolicyBinding(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<ValidatingAdmissionPolicyBinding.Version> {
    interface Version : APIVersion

    data class Spec(
        val matchResources: MatchResources? = null,
        val paramRef: ParamRef? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
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

    class Builder : ResourceSpecDSLBuilder<ValidatingAdmissionPolicyBinding, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): ValidatingAdmissionPolicyBinding {
            return ValidatingAdmissionPolicyBinding(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ValidatingAdmissionPolicyBinding, Builder>(Builder()) {
        fun binding(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}