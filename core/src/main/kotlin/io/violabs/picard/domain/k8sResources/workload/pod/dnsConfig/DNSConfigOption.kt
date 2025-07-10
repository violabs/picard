package io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DslBuilder

data class DNSConfigOption(val name: String, val value: String? = null) {
    class Builder : DslBuilder<DNSConfigOption> {
        var name: String? = null
        var value: String? = null
        override fun build(): DNSConfigOption {
            return DNSConfigOption(
                vRequireNotNull(this::name),
                value
            )
        }
    }

    class Group : BuilderGroup<DNSConfigOption, Builder>(Builder()) {
        fun options(): List<DNSConfigOption>? = items()

        fun addDNSConfigOption(block: Builder.() -> Unit) {
            add(block)
        }
    }
}