package io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig


import io.violabs.picard.common.BuilderGroup

class DNSConfigOptionGroup : BuilderGroup<DNSConfigOption, DNSConfigOption.Builder>(DNSConfigOption.Builder()) {
    fun options(): MutableList<DNSConfigOption>? = items()

    fun option(scope: DNSConfigOption.Builder.() -> Unit) {
        add(scope)
    }
}