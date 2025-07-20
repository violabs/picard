package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BooleanType
import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder

data class OnPodConditionsPattern(
    val status: BooleanType,
    val type: String
) {
    class Builder : DslBuilder<OnPodConditionsPattern> {
        var status: BooleanType? = null
        var type: String? = null

        override fun build(): OnPodConditionsPattern {
            return OnPodConditionsPattern(
                status = vRequireNotNull(this::status),
                type = vRequireNotNull(this::type)
            )
        }
    }

    class Group : BuilderGroup<OnPodConditionsPattern, Builder>(Builder()) {
        fun patterns(): List<OnPodConditionsPattern>? = items()

        fun addOnPodConditionsPattern(block: Builder.() -> Unit) {
            add(block)
        }
    }
}