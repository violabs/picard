package io.violabs.picard.domain

data class Taint(
    val effect: Effect,
) {
    enum class Effect {
        NoSchedule,
        PreferNoSchedule,
        NoExecute
    }
}