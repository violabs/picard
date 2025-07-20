package io.violabs.picard.domain

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DslBuilder
import io.violabs.picard.common.vRequireNotNull

data class Taint(
    val effect: Effect,
) {
    enum class Effect {
        NoSchedule,
        PreferNoSchedule,
        NoExecute
    }

    class Builder : DslBuilder<Taint> {
        var effect: Effect? = null

        override fun build(): Taint {
            return Taint(
                effect = vRequireNotNull(this::effect)
            )
        }
    }

    class Group : BuilderGroup<Taint, Builder>(Builder()) {
        fun taints(): List<Taint>? = items()

        fun addTaint(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}