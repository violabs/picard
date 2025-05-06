package io.violabs.picard.domain.k8sResources.workload.pod.dnsConfig

import io.violabs.picard.common.DSLBuilder

data class DNSConfig(
    val nameservers: List<String>? = null,
    val options: List<DNSConfigOption>? = null,
    val searches: List<String>? = null,
    val dnsPolicy: String? = null
) {

    class Builder : DSLBuilder<DNSConfig> {
        private var nameservers: List<String>? = null
        private var options: List<DNSConfigOption>? = null
        private var searches: List<String>? = null
        var dnsPolicy: String? = null

        fun nameservers(vararg nameservers: String) {
            this.nameservers = nameservers.toList()
        }

        fun options(scope: DNSConfigOption.Group.() -> Unit) {
            options = DNSConfigOption.Group().apply(scope).options()
        }

        fun searches(vararg searches: String) {
            this.searches = searches.toList()
        }

        override fun build(): DNSConfig {
            return DNSConfig(
                nameservers,
                options,
                searches,
                dnsPolicy
            )
        }
    }
}