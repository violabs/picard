package io.violabs.picard.v2.resources.workload.batch.cron

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/workload-resources/cron-job-v1/
 *
 * CronJob represents the configuration of a single cron job.
 *
 * apiVersion: batch/v1
 *
 * import "k8s.io/api/batch/v1"
 *
 * CronJob
 * CronJob represents the configuration of a single cron job.
 *
 * apiVersion: batch/v1
 */
@GeneratedDsl(withListGroup = true)
data class CronJob(
    @DefaultValue(
        "KAPIVersion.BatchV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of a cron job, including the schedule.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: CronJobSpec? = null,
    /**
     * Current status of a cron job.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: CronJobStatus? = null
) : WorkloadResource<CronJob.Version, ObjectMeta> {
    interface Version : APIVersion
}