package io.violabs.picard.v2.resources.extend.deviceclass

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ExtendResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * DeviceClass is a vendor- or admin-provided resource that contains device configuration and selectors. 
 * It can be referenced in the device requests of a claim to apply these presets. Cluster scoped.
 * 
 * This is an alpha type and requires enabling the DynamicResourceAllocation feature gate.
 * 
 * apiVersion: resource.k8s.io/v1beta2
 * 
 * import "k8s.io/api/resource/v1beta2"
 * 
 * https://kubernetes.io/docs/reference/kubernetes-api/extend-resources/device-class-v1beta2/
 */
@GeneratedDsl(withListGroup = true)
data class DeviceClass(
    @DefaultValue(
        "KAPIVersion.ResourceV1Beta2", 
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE, 
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.ResourceV1Beta2,
    /**
     * Standard object metadata
     */
    override val metadata: ObjectMeta? = null,
    /**
     * Spec defines what can be allocated and how to configure it.
     * 
     * This is mutable. Consumers have to be prepared for classes changing at any time, either because 
     * they get updated or replaced. Claim allocations are done once based on whatever was set in classes 
     * at the time of allocation.
     * 
     * Changing the spec automatically increments the metadata.generation number.
     */
    val spec: DeviceClassSpec
) : ExtendResource<DeviceClass.Version, ObjectMeta> {
    interface Version : APIVersion
}