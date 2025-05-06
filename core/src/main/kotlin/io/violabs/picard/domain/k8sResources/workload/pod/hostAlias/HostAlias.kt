package io.violabs.picard.domain.k8sResources.workload.pod.hostAlias

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class HostAlias(
    val ip: String,
    val hostnames: List<String>? = null
) {

    class Builder : DSLBuilder<HostAlias> {
        var ip: String? = null
        private var hostnames: List<String>? = null

        fun hostnames(vararg hostnames: String) {
            this.hostnames = hostnames.toList()
        }

        override fun build(): HostAlias {
            return HostAlias(
                ip = vRequireNotNull(this::ip),
                hostnames = hostnames
            )
        }
    }

    class Group : BuilderGroup<HostAlias, Builder>(Builder()) {
        fun hostAliases(): List<HostAlias>? = items()

        fun addHostAlias(block: Builder.() -> Unit) {
            add(block)
        }
    }
}