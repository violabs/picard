package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.DSLBuilder
import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookConversion

data class CustomResourceConversion(
    val strategy: String,
    val webhook: WebhookConversion? = null
) {
    class Builder : DSLBuilder<CustomResourceConversion> {
        var strategy: String? = null
        private var webhook: WebhookConversion? = null

        fun webhook(block: WebhookConversion.Builder.() -> Unit) {
            this.webhook = WebhookConversion.Builder().apply(block).build()
        }

        override fun build(): CustomResourceConversion  {
            return CustomResourceConversion(
                strategy = vRequireNotNull(this::strategy),
                webhook = webhook
            )
        }
    }
}