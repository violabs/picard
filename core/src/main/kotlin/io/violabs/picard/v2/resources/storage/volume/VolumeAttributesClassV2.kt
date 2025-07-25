package io.violabs.picard.v2.resources.storage.volume

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/volume-attributes-class-v1beta1/
 *
 * VolumeAttributesClass represents a specification of mutable volume attributes defined
 * by the CSI driver. The class can be specified during dynamic provisioning of
 * PersistentVolumeClaims, and changed in the PersistentVolumeClaim spec after provisioning.
 *
 * apiVersion: storage.k8s.io/v1beta1
 *
 * import "k8s.io/api/storage/v1beta1"
 */
@GeneratedDsl
data class VolumeAttributesClassV2(
    @DefaultValue(
        "KAPIVersion.StorageV1Beta1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1Beta1,
    override val metadata: ObjectMeta? = null,
    /**
     * Name of the CSI driver This field is immutable.
     */
    val driverName: String,
    /**
     * parameters hold volume attributes defined by the CSI driver. These values are opaque
     * to the Kubernetes and are passed directly to the CSI driver. The underlying storage
     * provider supports changing these attributes on an existing volume, however the
     * parameters field itself is immutable. To invoke a volume update, a new
     * VolumeAttributesClass should be created with new parameters, and the
     * PersistentVolumeClaim should be updated to reference the new VolumeAttributesClass.
     *
     * This field is required and must contain at least one key/value pair. The keys
     * cannot be empty, and the maximum number of parameters is 512, with a cumulative
     * max size of 256K. If the CSI driver rejects invalid parameters, the target
     * PersistentVolumeClaim will be set to an "Infeasible" state in the modifyVolumeStatus
     * field.
     */
    val parameters: Map<String, String>? = null
) : ConfigResource<VolumeAttributesClassV2.Version, ObjectMeta> {
    interface Version : APIVersion
}