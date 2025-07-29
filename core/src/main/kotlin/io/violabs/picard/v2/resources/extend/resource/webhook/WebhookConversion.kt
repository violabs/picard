package io.violabs.picard.v2.resources.extend.resource.webhook

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * WebhookConversion describes how to call a conversion webhook
 */
@GeneratedDsl
data class WebhookConversion(
    /**
     * conversionReviewVersions is an ordered list of preferred ConversionReview versions the Webhook expects. 
     * The API server will use the first version in the list which it supports. If none of the versions specified 
     * in this list are supported by API server, conversion will fail for the custom resource. If a persisted 
     * Webhook configuration specifies allowed versions and does not include any versions known to the API Server, 
     * calls to the webhook will fail.
     */
    val conversionReviewVersions: List<String>? = null,
    /**
     * clientConfig is the instructions for how to call the webhook if strategy is Webhook.
     */
    val clientConfig: WebhookClientConfig? = null
)