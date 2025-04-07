package io.violabs.picard.domain.k8sResources.extend.webhook

import io.violabs.picard.domain.k8sResources.workload.BaseServiceReference

data class WebhookClientConfig(
    val caBundle: List<Byte>? = null,
    val service: ServiceReference? = null,
    val url: String? = null
) {
    data class ServiceReference(
        val name: String? = null,
        val namespace: String? = null,
        val port: Int? = null
    ) : BaseServiceReference
}