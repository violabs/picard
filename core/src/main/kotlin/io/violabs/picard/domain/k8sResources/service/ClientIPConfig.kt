package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.common.DslBuilder

data class ClientIPConfig(val timeoutSeconds: Int? = null) {
    class Builder : DslBuilder<ClientIPConfig> {
        var timeoutSeconds: Int? = null

        override fun build(): ClientIPConfig {
            return ClientIPConfig(
                timeoutSeconds = timeoutSeconds
            )
        }
    }
}