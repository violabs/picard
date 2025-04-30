package io.violabs.picard.domain.k8sResources.service

import io.violabs.picard.domain.DSLBuilder

data class ClientIPConfig(val timeoutSeconds: Int? = null) {
    class Builder : DSLBuilder<ClientIPConfig> {
        var timeoutSeconds: Int? = null

        override fun build(): ClientIPConfig {
            return ClientIPConfig(
                timeoutSeconds = timeoutSeconds
            )
        }
    }
}