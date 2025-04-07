package io.violabs.picard.domain.k8sResources.extend.webhook

data class WebhookConversion(
    val conversionReviewVersions: List<String>,
    val clientConfig: WebhookClientConfig? = null
)