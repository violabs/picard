package io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig

import io.violabs.picard.domain.DslBuilder

data class DNSConfigOption(val name: String, val value: String? = null) {
    class Builder : DslBuilder<DNSConfigOption> {
        var name: String? = null
        var value: String? = null
        override fun build(): DNSConfigOption {
            return DNSConfigOption(
                requireNotNull(name),
                value
            )
        }
    }
}