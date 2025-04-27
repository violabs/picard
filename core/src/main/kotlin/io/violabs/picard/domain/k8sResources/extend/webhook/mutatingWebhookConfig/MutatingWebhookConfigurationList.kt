package io.violabs.picard.domain.k8sResources.extend.webhook.mutatingWebhookConfig

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.K8sListResource

data class MutatingWebhookConfigurationList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<MutatingWebhookConfiguration>,
    override val metadata: ListMeta? = null
) : K8sListResource<MutatingWebhookConfigurationList.Version, MutatingWebhookConfiguration> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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
