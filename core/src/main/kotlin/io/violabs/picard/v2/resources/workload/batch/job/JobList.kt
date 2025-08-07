package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class JobList(
    @DefaultValue(
        "KAPIVersion.BatchV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val items: List<Job>,
    override val metadata: ListMeta? = null
) : K8sListResource<JobList.Version, Job> {
    interface Version : APIVersion
}
