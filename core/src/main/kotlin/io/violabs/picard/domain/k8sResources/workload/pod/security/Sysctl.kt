package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DslBuilder

data class Sysctl(
    val name: String,
    val value: String
) {
    class Builder : DslBuilder<Sysctl> {
        var name: String? = null
        var value: String? = null

        override fun build(): Sysctl {
            return Sysctl(
                name = vRequireNotNull(this::name),
                value = vRequireNotNull(this::value)
            )
        }
    }
}