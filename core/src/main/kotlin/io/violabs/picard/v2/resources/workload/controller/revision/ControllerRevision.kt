package io.violabs.picard.v2.resources.workload.controller.revision

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * ControllerRevision implements an immutable snapshot of state data.
 */
@GeneratedDsl(withListGroup = true)
data class ControllerRevision(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Revision indicates the revision of the state represented by Data.
     */
    val revision: Long,
    /**
     * Data is the serialized representation of the state.
     */
    val data: Any? = null
) : WorkloadResource<ControllerRevision.Version, ObjectMeta> {
    interface Version : APIVersion
}