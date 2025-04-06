package io.violabs.picard.domain.k8sResources.workload.cronJob

import io.violabs.picard.domain.K8sEnum
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.K8sResource
import io.violabs.picard.domain.k8sResources.workload.BaseSpec
import io.violabs.picard.domain.k8sResources.workload.BaseStatus
import io.violabs.picard.domain.k8sResources.workload.JobCondition
import io.violabs.picard.domain.k8sResources.workload.job.JobTemplate
import java.time.LocalDateTime

class CronJob(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : K8sResource<CronJob.Version> {
    interface Version : APIVersion

    data class Spec(
        val jobTemplate: JobTemplate.Spec,
        val schedule: String,
        val timeZone: String? = null,
        val concurrencyPolicy: String? = null,
        val startingDeadlineSeconds: Long? = null,
        val suspend: Boolean? = null,
        val successfulJobsHistoryLimit: Int? = null,
        val failedJobsHistoryLimit: Int? = null
    ) : BaseSpec {
        enum class ConcurrencyPolicy : K8sEnum {
            ALLOW,
            FORBID,
            REPLACE;

            override fun toString(): String = properCase()
        }
    }

    data class Status(
        val active: List<ObjectReference>? = null,
        val lastScheduleTime: LocalDateTime? = null,
        val lastSuccessfulTime: LocalDateTime? = null
    ) : BaseStatus
}