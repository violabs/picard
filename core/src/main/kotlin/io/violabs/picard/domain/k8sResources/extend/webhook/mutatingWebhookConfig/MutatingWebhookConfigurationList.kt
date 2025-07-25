package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendListResource

data class MutatingWebhookConfigurationList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<MutatingWebhookConfiguration>,
    override val metadata: ListMeta? = null
) : ExtendListResource<MutatingWebhookConfigurationList.Version, MutatingWebhookConfiguration> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        MutatingWebhookConfiguration,
        MutatingWebhookConfiguration.Builder,
        MutatingWebhookConfiguration.Group,
        MutatingWebhookConfigurationList
        >(MutatingWebhookConfiguration.Group()) {

        override fun build(): MutatingWebhookConfigurationList {
            return MutatingWebhookConfigurationList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
