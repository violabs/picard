package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class JobList(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val items: List<Job>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<JobList.Version, Job> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
        Job,
        Job.Builder,
        Job.Group,
        JobList
        >(Job.Group()) {

        override fun build(): JobList {
            return JobList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}