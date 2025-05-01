package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.BooleanType
import io.violabs.picard.domain.BuilderGroup
import io.violabs.picard.domain.DSLBuilder

data class OnPodConditionsPattern(
    val status: BooleanType,
    val type: String
) {
    class Builder : DSLBuilder<OnPodConditionsPattern> {
        var status: BooleanType? = null
        var type: String? = null

        override fun build(): OnPodConditionsPattern {
            return OnPodConditionsPattern(
                status = requireNotNull(status),
                type = requireNotNull(type)
            )
        }
    }

    class Group : BuilderGroup<OnPodConditionsPattern, Builder>(Builder()) {
        fun patterns(): List<OnPodConditionsPattern>? = items()

        fun pattern(block: Builder.() -> Unit) {
            add(block)
        }
    }
}