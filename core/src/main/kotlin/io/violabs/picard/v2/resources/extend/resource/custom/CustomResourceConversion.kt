package io.violabs.picard.v2.resources.extend.resource.custom

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.v2.resources.extend.resource.webhook.WebhookConversion

/**
 * CustomResourceConversion describes how to convert different versions of a CR.
 */
@GeneratedDsl
data class CustomResourceConversion(
    /**
     * strategy specifies how custom resources are converted between versions. Allowed values are:
     * - "None": The converter only change the apiVersion and would not touch any other field in the custom resource.
     * - "Webhook": API Server will call to an external webhook to do the conversion. Additional information 
     *   is needed for this option. This requires spec.preserveUnknownFields to be false, and spec.conversion.webhook to be set.
     */
    val strategy: String? = null,
    /**
     * webhook describes how to call the conversion webhook. Required when strategy is set to "Webhook".
     */
    val webhook: WebhookConversion? = null
)