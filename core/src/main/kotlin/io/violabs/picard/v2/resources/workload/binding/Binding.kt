package io.violabs.picard.v2.resources.workload.binding

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.ClusterResource
import io.violabs.picard.v2.common.ObjectMeta
import io.violabs.picard.v2.common.ObjectReference

/**
 * Binding ties one object to another; for example, a pod is bound to a node by a scheduler.
 *
 * apiVersion: v1
 */
@GeneratedDsl
data class Binding(
    @DefaultValue(
        "KAPIVersion.V1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.V1,
    override val metadata: ObjectMeta? = null,
    /**
     * The target object that you want to bind to the standard object.
     */
    val target: ObjectReference
) : ClusterResource<Binding.Version, ObjectMeta> {
    interface Version : APIVersion
}