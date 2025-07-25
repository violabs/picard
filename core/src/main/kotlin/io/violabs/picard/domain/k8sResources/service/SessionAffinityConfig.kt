package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.DslBuilder

data class SessionAffinityConfig(
    val clientIP: ClientIPConfig? = null
) {
    class Builder : DslBuilder<SessionAffinityConfig> {
        private var clientIP: ClientIPConfig? = null

        fun clientIP(scope: ClientIPConfig.Builder.() -> Unit) {
            clientIP = ClientIPConfig.Builder().apply(scope).build()
        }

        override fun build(): SessionAffinityConfig {
            return SessionAffinityConfig(
                clientIP = clientIP
            )
        }
    }
}