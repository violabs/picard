package io.violabs.picard.domain.k8sResources.service.ingressClass

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ServiceResource


data class IngressClass(
    override val apiVersion: Version = KAPIVersion.NetworkingV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
) : ServiceResource<IngressClass.Version, ObjectMetadata> {
    interface Version : APIVersion

    data class Spec(
        val controller: String? = null,
        val parameters: IngressClassParametersReference? = null
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
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

    class Builder : ResourceSpecDslBuilder<IngressClass, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): IngressClass {
            return IngressClass(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<IngressClass, Builder>(Builder()) {
        fun ingressClassItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}