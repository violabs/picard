package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class MutatingWebhookConfiguration(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val webhooks: List<MutatingWebhook>? = null
) : K8sResource<MutatingWebhookConfiguration.Version> {
    interface Version : APIVersion
}