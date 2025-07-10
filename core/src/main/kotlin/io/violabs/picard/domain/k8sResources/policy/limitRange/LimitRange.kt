package io.violabs.picard.domain.k8sResources.policy.limitRange

import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.ResourceSpecDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.PolicyResource

data class LimitRange(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : PolicyResource<LimitRange.Version> {
    interface Version : APIVersion

    data class Spec(
        val limits: List<LimitRangeItem>
    ) : BaseSpec {
        class Builder : DslBuilder<Spec> {
            private var limits: List<LimitRangeItem>? = null

            fun limits(scope: LimitRangeItem.Group.() -> Unit) {
                this.limits = LimitRangeItem.Group().apply(scope).limitRangeItems()
            }

            override fun build(): Spec {
                return Spec(
                    limits = vRequireNotEmpty(this::limits)
                )
            }
        }
    }

    class Builder : ResourceSpecDslBuilder<LimitRange, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): LimitRange {
            return LimitRange(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<LimitRange, Builder>(Builder()) {
        fun limitRangeItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}