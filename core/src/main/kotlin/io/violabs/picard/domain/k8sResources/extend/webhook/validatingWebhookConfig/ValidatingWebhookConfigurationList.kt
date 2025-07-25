package io.violabs.picard.domain.k8sResources.extend.webhook.validatingWebhookConfig

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendListResource

data class ValidatingWebhookConfigurationList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<ValidatingWebhookConfiguration>,
    override val metadata: ListMeta? = null
) : ExtendListResource<ValidatingWebhookConfigurationList.Version, ValidatingWebhookConfiguration> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        ValidatingWebhookConfiguration,
        ValidatingWebhookConfiguration.Builder,
        ValidatingWebhookConfiguration.Group,
        ValidatingWebhookConfigurationList
        >(ValidatingWebhookConfiguration.Group()) {

        override fun build(): ValidatingWebhookConfigurationList {
            return ValidatingWebhookConfigurationList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}
