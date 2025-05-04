package io.violabs.picard.domain.k8sResources.service.ingressClass

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecDSLBuilder
import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.K8sListResource


data class IngressClass(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
) : K8sResource<IngressClass.Version> {
    interface Version : APIVersion

    data class Spec(
        val controller: String? = null,
        val parameters: IngressClassParametersReference? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            var controller: String? = null
            private var parameters: IngressClassParametersReference? = null

            fun parameters(block: IngressClassParametersReference.Builder.() -> Unit) {
                parameters = IngressClassParametersReference.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    controller = controller,
                    parameters = parameters
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<IngressClass, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): IngressClass {
            return IngressClass(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<IngressClass, Builder>(Builder()) {
        fun ingressClass(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}