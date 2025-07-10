package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendResource

data class ValidatingWebhookConfiguration(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val webhooks: List<ValidatingWebhook>? = null
) : ExtendResource<ValidatingWebhookConfiguration.Version> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<ValidatingWebhookConfiguration>() {
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