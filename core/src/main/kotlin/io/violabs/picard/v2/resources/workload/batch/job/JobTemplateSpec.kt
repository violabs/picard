package io.violabs.picard.v2.resources.workload.batch.job

import io.violabs.konstellation.metaDsl.annotation.GeneratedDsl
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.k8sResources.workload.job.Job

/**
 * JobTemplateSpec describes the data a Job should have when created from a template
 */
@GeneratedDsl
data class JobTemplateSpec(
    /**
     * Standard object's metadata of the jobs created from this template.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata
     */
    val metadata: ObjectMetadata? = null,
    /**
     * Specification of the desired behavior of the job.
     * More info: https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status
     */
    val spec: Job.Spec? = null
)