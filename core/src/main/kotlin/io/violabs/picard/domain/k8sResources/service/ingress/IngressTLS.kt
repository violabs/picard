package io.violabs.picard.domain.k8sResources.service.ingress

import io.violabs.picard.common.BuilderGroup
import io.violabs.picard.common.DSLBuilder

data class IngressTLS(
    val hosts: List<String>? = null,
    val secretName: String? = null
) {
    class Builder : DSLBuilder<IngressTLS> {
        private var hosts: List<String>? = null
        var secretName: String? = null

        fun hosts(vararg hosts: String) {
            this.hosts = hosts.toList()
        }

        override fun build(): IngressTLS {
            return IngressTLS(
                hosts = hosts,
                secretName = secretName
            )
        }
    }

    class Group : BuilderGroup<IngressTLS, Builder>(Builder()) {
        fun tlsList(): List<IngressTLS>? = items()

        fun addIngressTLS(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}