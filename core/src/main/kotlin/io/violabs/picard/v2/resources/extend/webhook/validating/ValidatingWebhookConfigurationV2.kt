package io.violabs.picard.v2.resources.extend.webhook.validating

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/extend-resources/validating-webhook-configuration-v1/
 *
 * ValidatingWebhookConfiguration describes the configuration of an admission webhook
 * that accepts or rejects an object without changing it.
 *
 * apiVersion: admissionregistration.k8s.io/v1
 *
 * import "k8s.io/api/admissionregistration/v1"
 */
@GeneratedDsl
data class ValidatingWebhookConfigurationV2(
    @DefaultValue(
        "KAPIVersion.AdmissionRegistrationV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AdmissionRegistrationV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Webhooks is a list of webhooks and the affected resources and operations.
     */
    val webhooks: List<ValidatingWebhook>? = null
) : ExtendResource<ValidatingWebhookConfigurationV2.Version, ObjectMeta> {
    interface Version : APIVersion
}