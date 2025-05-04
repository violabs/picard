package io.violabs.picard.domain.k8sResources.workload.cronJob

import io.violabs.picard.common.vRequireNotEmpty
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.common.ResourceListDSLBuilder
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion

data class CronJobList(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val items: List<CronJob>,
    override val metadata: ListMeta? = null
) : K8sListResource<CronJobList.Version, CronJob> {
    interface Version : APIVersion

    class Builder : ResourceListDSLBuilder<
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