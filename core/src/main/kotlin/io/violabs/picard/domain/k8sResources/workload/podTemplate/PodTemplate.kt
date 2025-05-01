package io.violabs.picard.domain.k8sResources.workload.podTemplate

import io.violabs.picard.domain.*
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.workload.pod.Pod

data class PodTemplate(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<PodTemplate.Version> {
    interface Version : APIVersion

    data class Spec(
        val metadata: ObjectMetadata? = null,
        val spec: Pod.Spec? = null
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
            private var metadata: ObjectMetadata? = null
            private var spec: Pod.Spec? = null

            fun metadata(block: ObjectMetadata.Builder.() -> Unit) {
                metadata = ObjectMetadata.Builder().apply(block).build()
            }

            fun spec(block: Pod.Spec.Builder.() -> Unit) {
                spec = Pod.Spec.Builder().apply(block).build()
            }

            override fun build(): Spec {
                return Spec(
                    metadata = metadata,
                    spec = spec
                )
            }
        }
    }

    class Builder : ResourceSpecDSLBuilder<PodTemplate, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): PodTemplate {
            return PodTemplate(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<PodTemplate, Builder>(Builder()) {
        fun template(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}