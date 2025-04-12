package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.DslBuilder

data class Sysctl(
    val name: String,
    val value: String
) {
    class Builder : DslBuilder<Sysctl> {
        val name: String? = null
        val value: String? = null

        override fun build(): Sysctl {
            return Sysctl(
                name = requireNotNull(name),
                value = requireNotNull(value)
            )
        }
    }
}