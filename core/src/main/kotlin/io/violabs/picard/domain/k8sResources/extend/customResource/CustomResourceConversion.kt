package io.violabs.picard.domain.k8sResources.extend.customResource

import io.violabs.picard.domain.k8sResources.extend.webhook.WebhookConversion

data class CustomResourceConversion(
    val strategy: String,
    val webhook: WebhookConversion? = null
)