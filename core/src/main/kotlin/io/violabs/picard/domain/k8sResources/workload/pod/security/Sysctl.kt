package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder

data class Sysctl(
    val name: String,
    val value: String
) {
    class Builder : DSLBuilder<Sysctl> {
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