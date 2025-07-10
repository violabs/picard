package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.domain.BaseServiceReference
import io.violabs.picard.common.DslBuilder

data class WebhookClientConfigServiceReference(
    val name: String? = null,
    val namespace: String? = null,
    val port: Int? = null
) : BaseServiceReference {
    class Builder : DslBuilder<WebhookClientConfigServiceReference> {
        var name: String? = null
        var namespace: String? = null
        var port: Int? = null

        override fun build(): WebhookClientConfigServiceReference {
            return WebhookClientConfigServiceReference(
                name = name,
                namespace = namespace,
                port = port
            )
        }
    }
}