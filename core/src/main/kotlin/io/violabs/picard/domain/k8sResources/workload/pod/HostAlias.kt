package io.violabs.picard.domain.k8sResources.workload.pod

import io.violabs.picard.domain.DslBuilder

class HostAlias(
    val ip: String,
    val hostnames: List<String>? = null
) {

    class Builder : DslBuilder<HostAlias> {
        var ip: String? = null
        private var hostnames: List<String>? = null

        fun hostnames(vararg hostnames: String) {
            this.hostnames = hostnames.toList()
        }

        override fun build(): HostAlias {
            return HostAlias(
                ip = requireNotNull(ip),
                hostnames = hostnames
            )
        }
    }
}