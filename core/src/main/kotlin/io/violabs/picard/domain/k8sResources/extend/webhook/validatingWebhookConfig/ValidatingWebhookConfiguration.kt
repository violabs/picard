package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig

import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ResourceDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class ValidatingWebhookConfiguration(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val webhooks: List<ValidatingWebhook>? = null
) : K8sResource<ValidatingWebhookConfiguration.Version> {
    interface Version : APIVersion

    class Builder : ResourceDSLBuilder<ValidatingWebhookConfiguration>() {
        private var webhooks: List<ValidatingWebhook>? = null

        fun webhooks(scope: ValidatingWebhook.Group.() -> Unit) {
            webhooks = ValidatingWebhook.Group().apply(scope).webhooks()
        }

        override fun build(): ValidatingWebhookConfiguration {
            return ValidatingWebhookConfiguration(
                metadata = metadata,
                webhooks = webhooks
            )
        }
    }

    class Group : K8sListResource.ItemGroup<ValidatingWebhookConfiguration, Builder>(Builder()) {
        fun config(scope: Builder.() -> Unit) {
            add(scope)
        }
    }
}