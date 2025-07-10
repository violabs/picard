package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.common.ResourceDslBuilder
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendResource

data class MutatingWebhookConfiguration(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMetadata? = null,
    val webhooks: List<MutatingWebhook>? = null
) : ExtendResource<MutatingWebhookConfiguration.Version> {
    interface Version : APIVersion

    class Builder : ResourceDslBuilder<MutatingWebhookConfiguration>() {
        private var webhooks: List<MutatingWebhook>? = null

        fun webhooks(scope: MutatingWebhook.Group.() -> Unit) {
            webhooks = MutatingWebhook.Group().apply(scope).webhooks()
        }

        override fun build(): MutatingWebhookConfiguration {
            return MutatingWebhookConfiguration(
                metadata = metadata,
                webhooks = webhooks
            )
        }
    }

    class Group : K8sListResource.ItemGroup<MutatingWebhookConfiguration, Builder>(Builder()) {
        fun config(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}