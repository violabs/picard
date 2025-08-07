package io.violabs.picard.v2.resources.workload.batch.cron

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ListMeta
import io.violabs.picard.domain.k8sResources.K8sListResource

data class CronJobListV2(
    @DefaultValue(
        "KAPIVersion.BatchV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val items: List<CronJobV2>,
    override val metadata: ListMeta? = null
) : K8sListResource<CronJobListV2.Version, CronJobV2> {
    interface Version : APIVersion
}
