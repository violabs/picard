package io.violabs.picard.v2.resources.workload.daemon

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * DaemonSet represents the configuration of a daemon set.
 */
@GeneratedDsl
data class DaemonSetV2(
    @DefaultValue(
        "KAPIVersion.AppsV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.AppsV1,
    override val metadata: ObjectMeta? = null,
    /**
     * The desired behavior of this daemon set.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: DaemonSetSpec? = null,
    /**
     * The current status of this daemon set. This data may be out of date by some
     * window of time. Populated by the system. Read-only.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: DaemonSetStatus? = null
) : WorkloadResource<DaemonSetV2.Version, ObjectMeta> {
    interface Version : APIVersion
}