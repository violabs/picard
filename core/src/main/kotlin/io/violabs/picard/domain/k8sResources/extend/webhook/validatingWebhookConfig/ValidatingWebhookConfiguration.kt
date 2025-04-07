package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

class ValidatingWebhookConfiguration(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val webhooks: List<ValidatingWebHook>? = null
) : K8sResource<ValidatingWebhookConfiguration.Version> {

    interface Version : APIVersion
}