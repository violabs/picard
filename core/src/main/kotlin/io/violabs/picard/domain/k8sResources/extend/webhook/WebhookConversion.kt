package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.common.DslBuilder

data class WebhookConversion(
    val conversionReviewVersions: List<String>,
    val clientConfig: WebhookClientConfig? = null
) {
    class Builder : DslBuilder<WebhookConversion> {
        private var conversionReviewVersions: List<String>? = null
        private var clientConfig: WebhookClientConfig? = null

        fun conversionReviewVersions(vararg versions: String) {
            this.conversionReviewVersions = versions.toList()
        }

        fun clientConfig(block: WebhookClientConfig.Builder.() -> Unit) {
            this.clientConfig = WebhookClientConfig.Builder().apply(block).build()
        }

        override fun build(): WebhookConversion {
            return WebhookConversion(
                conversionReviewVersions = vRequireNotEmpty(this::conversionReviewVersions),
                clientConfig = clientConfig
            )
        }
    }
}