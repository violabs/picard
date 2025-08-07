package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.konstellation.metaDsl.annotation.DefaultValue
import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.common.AppConstants
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.manifest.WorkloadResource
import io.violabs.picard.v2.common.ObjectMeta

/**
 * https://kubernetes.io/docs/reference/kubernetes-api/workload-resources/job-v1/
 *
 * Job represents the configuration of a single job.
 *
 * apiVersion: batch/v1
 *
 * import "k8s.io/api/batch/v1"
 *
 * Job
 * Job represents the configuration of a single job.
 *
 * apiVersion: batch/v1
 */
@GeneratedDsl(withListGroup = true)
data class Job(
    @DefaultValue(
        "KAPIVersion.BatchV1",
        AppConstants.DefaultValue.KAPI_VERSION_PACKAGE,
        AppConstants.DefaultValue.KAPI_VERSION_CLASS
    )
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMeta? = null,
    /**
     * Specification of the desired behavior of a job.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: JobSpec? = null,
    /**
     * Current status of a job.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val status: JobStatus? = null
) : WorkloadResource<Job.Version, ObjectMeta> {
    interface Version : APIVersion
}