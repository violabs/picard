package io.violabs.picard.v2.resources.cluster.component.status

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ComponentStatus (and ComponentStatusList) holds the cluster validation info.
 * Deprecated: This API is deprecated in v1.19+
 *
 * apiVersion: v1
 *
 * import "k8s.io/api/core/v1"
 *
 * ComponentStatus (and ComponentStatusList) holds the cluster validation info. Deprecated: This API is deprecated in v1.19+
 *
 * apiVersion: v1
 *
 * kind: ComponentStatus
 *
 * metadata (ObjectMeta)
 *
 * Standard object's metadata. More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
 *
 * conditions ([]ComponentCondition)
 *
 * Patch strategy: merge on key type
 *
 * Map: unique values on key type will be kept during a merge
 *
 * List of component conditions observed
 */
@GeneratedDsl(withListGroup = true)
data class ComponentStatus(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    /**
     * List of component conditions observed
     * 
     * Patch strategy: merge on key type
     * Map: unique values on key type will be kept during a merge
     */
    val conditions: List<ComponentCondition>? = null
) : ClusterResource<ComponentStatus.Version, ObjectMeta> {
    interface Version : APIVersion

    override fun getKind(): String = "ComponentStatus"
}