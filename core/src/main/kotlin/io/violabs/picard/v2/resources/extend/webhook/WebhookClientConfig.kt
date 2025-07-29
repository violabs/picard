package io.violabs.picard.v2.resources.extend.webhook

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl

/**
 * WebhookClientConfig contains the information to make a TLS connection with the webhook
 */
@GeneratedDsl
data class WebhookClientConfig(
    /**
     * caBundle is a PEM encoded CA bundle which will be used to validate the webhook's server certificate.
     * If unspecified, system trust roots on the apiserver are used.
     */
    val caBundle: List<Byte>? = null,
    /**
     * service is a reference to the service for this webhook. Either service or url must be specified.
     * If the webhook is running within the cluster, then you should use service.
     */
    val service: ServiceReference? = null,
    /**
     * url gives the location of the webhook, in standard URL form (scheme://host:port/path).
     * Exactly one of url or service must be specified.
     */
    val url: String? = null
)