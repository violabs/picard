package io.violabs.picard.domain.k8sResources.workload.pod.security

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

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

    class Group : BuilderGroup<Sysctl, Builder>(Builder()) {
        fun sysctls(): List<Sysctl>? = items()

        fun addSysctl(block: Builder.() -> Unit) {
            add(block)
        }
    }
}