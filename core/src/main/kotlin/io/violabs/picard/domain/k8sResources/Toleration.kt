package io.violabs.picard.domain.k8sResources

import io.violabs.picard.domain.DslBuilder

data class Toleration(
    val key: String? = null,
    val operator: String? = null,
    val value: String? = null,
    val effect: String? = null,
    val tolerationSeconds: Long? = null
) {
    class Builder : DslBuilder<Toleration> {
        var key: String? = null
        var operator: String? = null
        var value: String? = null
        var effect: String? = null
        var tolerationSeconds: Long? = null
        override fun build(): Toleration {
            return Toleration(
                key = key,
                operator = operator,
                value = value,
                effect = effect,
                tolerationSeconds = tolerationSeconds
            )
        }
    }
}