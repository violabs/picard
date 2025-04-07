package io.violabs.picard.domain

data class Taint(
    val effect: Effect,
) {
    enum class Effect : K8sEnum {
        NO_SCHEDULE,
        PREFER_NO_SCHEDULE,
        NO_EXECUTE
    }
}