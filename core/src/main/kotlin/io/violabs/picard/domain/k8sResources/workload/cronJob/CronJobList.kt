package io.violabs.picard.domain.k8sResources.workload.cronJob

import io.violabs.picard.common.ResourceListDslBuilder
import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadListResource

data class CronJobList(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val items: List<CronJob>,
    override val metadata: ListMeta? = null
) : WorkloadListResource<CronJobList.Version, CronJob> {
    interface Version : APIVersion

    class Builder : ResourceListDslBuilder<
        CronJob,
        CronJob.Builder,
        CronJob.Group,
        CronJobList
        >(CronJob.Group()) {

        override fun build(): CronJobList {
            return CronJobList(
                items = vRequireNotEmpty(this::items),
                metadata = metadata
            )
        }
    }
}