package io.violabs.picard.v2.resources.storage.csi.driver

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ConfigResource
import io.violabs.picard.domain.manifest.StorageResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * CSIDriver captures information about a Container Storage Interface (CSI) volume driver deployed on the cluster.
 * apiVersion: storage.k8s.io/v1
 *
 * import "k8s.io/api/storage/v1"
 *
 * https://kubernetes.io/docs/reference/kubernetes-api/config-and-storage-resources/csi-driver-v1/
 */
@GeneratedDsl(withListGroup = true)
data class CsiDriver(
    @DefaultValue(
        "KAPIVersion.StorageV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.StorageV1,
    /**
     * Standard object metadata. metadata.Name indicates the name of the
     * CSI driver that this object refers to; it MUST be the same name returned by
     * the CSI GetPluginName() call for that driver. The driver name must be 63
     * characters or less, beginning and ending with an alphanumeric character
     * ([a-z0-9A-Z]) with dashes (-), dots (.), and alphanumerics between.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    override val metadata: ObjectMeta? = null,
    val spec: CsiDriverSpec? = null
) : StorageResource<CsiDriver.Version, ObjectMeta> {
    interface Version : APIVersion

    override fun getKind(): String {
        return "CSIDriver"
    }
}