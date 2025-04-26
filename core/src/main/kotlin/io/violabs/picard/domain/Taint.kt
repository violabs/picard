package io.violabs.picard.domain

data class Taint(
    val effect: Effect,
) {
    enum class Effect {
        NoSchedule,
        PreferNoSchedule,
        NoExecute
    }

    class Builder : DSLBuilder<Taint> {
        var effect: Effect? = null

        override fun build(): Taint {
            return Taint(
                effect = requireNotNull(effect)
            )
        }
    }

    class Group : BuilderGroup<Taint, Builder>(Builder()) {
        fun taints(): List<Taint>? = items()

        fun taint(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}