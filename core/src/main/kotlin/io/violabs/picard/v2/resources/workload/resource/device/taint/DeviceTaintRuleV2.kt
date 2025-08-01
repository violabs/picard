package io.violabs.picard.v2.resources.workload.resource.device.taint

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/extend-resources/device-taint-rule-v1alpha3/
 *
 * DeviceTaintRule adds one taint to all devices which match the selector. 
 * This has the same effect as if the taint was specified directly in the ResourceSlice by the DRA driver.
 *
 * apiVersion: resource.k8s.io/v1alpha3
 *
 * import "k8s.io/api/resource/v1alpha3"
 */
@GeneratedDsl
data class DeviceTaintRuleV2(
    @DefaultValue(
        "KAPIVersion.ResourceV1Alpha3",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.ResourceV1Alpha3,
    /**
     * Standard object metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Spec specifies the selector and one taint.
     * 
     * Changing the spec automatically increments the metadata.generation number.
     */
    val spec: DeviceTaintRuleSpec
) : ExtendResource<DeviceTaintRuleV2.Version, ObjectMeta> {
    interface Version : APIVersion
}