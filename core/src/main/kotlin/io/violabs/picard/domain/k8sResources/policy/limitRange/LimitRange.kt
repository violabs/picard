package io.violabs.picard.domain.k8sResources.policy.limitRange

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.ResourceSpecDSLBuilder
import io.violabs.picard.domain.k8sResources.*

data class LimitRange(
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null
) : K8sResource<LimitRange.Version> {
    interface Version : APIVersion

    data class Spec(
        val limits: List<LimitRangeItem>
    ) : BaseSpec {
        class Builder : DSLBuilder<Spec> {
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

    class Builder : ResourceSpecDSLBuilder<LimitRange, Spec, Spec.Builder>(Spec.Builder()) {
        override fun build(): LimitRange {
            return LimitRange(
                metadata = metadata,
                spec = spec
            )
        }
    }

    class Group : K8sListResource.ItemGroup<LimitRange, Builder>(Builder()) {
        fun range(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}