package io.violabs.picard.domain.k8sResources.extend

import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class MutatingWebhookConfigurationList(
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<MutatingWebhookConfiguration>,
    override val metadata: ListMeta? = null
) : K8sListResource<MutatingWebhookConfigurationList.Version, MutatingWebhookConfiguration> {
    interface Version : APIVersion
}
