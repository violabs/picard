package io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.common.DSLBuilder

data class DNSConfigOption(val name: String, val value: String? = null) {
    class Builder : DSLBuilder<DNSConfigOption> {
        var name: String? = null
        var value: String? = null
        override fun build(): DNSConfigOption {
            return DNSConfigOption(
                vRequireNotNull(this::name),
                value
            )
        }
    }
}