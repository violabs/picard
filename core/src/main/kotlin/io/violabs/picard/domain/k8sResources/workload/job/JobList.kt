package io.violabs.picard.domain.k8sResources.workload.job

import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class JobList(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val items: List<Job>,
    override val metadata: ListMeta? = null
) : K8sListResource<JobList.Version, Job> {
    interface Version : APIVersion
}