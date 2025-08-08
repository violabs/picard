package io.violabs.picard.v2.resources.workload.scheduling

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.manifest.WorkloadListResource

@GeneratedDsl
data class PriorityClassList(
    @DefaultValue(
        "KAPIVersion.SchedulingV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.SchedulingV1,
    override val items: List<PriorityClass>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<PriorityClassList.Version, PriorityClass> {
    interface Version : APIVersion
}
