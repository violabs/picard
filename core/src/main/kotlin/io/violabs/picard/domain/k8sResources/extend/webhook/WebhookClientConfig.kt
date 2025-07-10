package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.common.DslBuilder

data class WebhookClientConfig(
    val caBundle: List<Byte>? = null,
    val service: WebhookClientConfigServiceReference? = null,
    val url: String? = null
) {
    class Builder : DslBuilder<WebhookClientConfig> {
        private var caBundle: List<Byte>? = null
        private var service: WebhookClientConfigServiceReference? = null
        var url: String? = null

        fun caBundle(vararg caBundle: Byte) {
            this.caBundle = caBundle.toList()
        }

        fun service(block: WebhookClientConfigServiceReference.Builder.() -> Unit) {
            this.service = WebhookClientConfigServiceReference.Builder().apply(block).build()
        }

        override fun build(): WebhookClientConfig {
            return WebhookClientConfig(
                caBundle = caBundle,
                service = service,
                url = url
            )
        }
    }
}