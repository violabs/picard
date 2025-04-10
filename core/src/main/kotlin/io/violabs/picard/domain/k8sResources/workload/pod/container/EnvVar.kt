package io.violabs.picard.domain.k8sResources.workload.pod.container

import io.violabs.picard.domain.DslBuilder

data class EnvVar(
    val name: String,
    val value: String? = null,
    val valueFrom: EnvVarSource? = null
) {
    class Builder : DslBuilder<EnvVar> {
        var name: String? = null
        var value: String? = null
        private var valueFrom: EnvVarSource? = null

        fun valueFrom(scope: EnvVarSource.Builder.() -> Unit) {
            valueFrom = EnvVarSource.Builder().apply(scope).build()
        }

        override fun build(): EnvVar {
            return EnvVar(
                name = requireNotNull(name) { "name must not be null" },
                value = value,
                valueFrom = valueFrom
            )
        }
    }
}