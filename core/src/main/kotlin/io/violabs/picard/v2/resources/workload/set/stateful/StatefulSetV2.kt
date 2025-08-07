package io.violabs.picard.v2.resources.workload.set.stateful

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * StatefulSet represents a set of pods with consistent identities.
 */
@GeneratedDsl(withListGroup = true)
data class StatefulSetV2(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Spec defines the desired identities of pods in this set.
     */
    val spec: StatefulSetSpec? = null,
    /**
     * Status is the current status of Pods in this StatefulSet. This data may be out of date by some window of time.
     */
    val status: StatefulSetStatus? = null
) : WorkloadResource<StatefulSetV2.Version, ObjectMeta> {
    interface Version : APIVersion
}