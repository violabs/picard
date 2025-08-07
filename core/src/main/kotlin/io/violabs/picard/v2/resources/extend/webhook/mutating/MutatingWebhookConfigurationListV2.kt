package io.violabs.picard.v2.resources.extend.webhook.mutating

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class MutatingWebhookConfigurationListV2(
    @DefaultValue(
        "KAPIVersion.AdmissionRegistrationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val items: List<MutatingWebhookConfigurationV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<MutatingWebhookConfigurationListV2.Version, MutatingWebhookConfigurationV2> {
    interface Version : APIVersion
}
