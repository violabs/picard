package io.violabs.picard.domain.k8sResources.workload.cronJob

import io.violabs.picard.common.DSLBuilder
import io.violabs.picard.common.ResourceSpecStatusDSLBuilder
import io.violabs.picard.common.vRequireNotNull
import io.violabs.picard.domain.BaseSpec
import io.violabs.picard.domain.BaseStatus
import io.violabs.picard.domain.ObjectMetadata
import io.violabs.picard.domain.ObjectReference
import io.violabs.picard.domain.k8sResources.APIVersion
import io.violabs.picard.domain.k8sResources.K8sListResource
import io.violabs.picard.domain.k8sResources.KAPIVersion
import io.violabs.picard.domain.k8sResources.workload.job.Job
import io.violabs.picard.domain.manifest.WorkloadResource
import java.time.LocalDateTime

data class CronJob(
    override val apiVersion: Version = KAPIVersion.BatchV1,
    override val metadata: ObjectMetadata? = null,
    val spec: Spec? = null,
    val status: Status? = null
) : WorkloadResource<CronJob.Version> {
    interface Version : APIVersion

    data class Spec(
        val jobTemplate: Job.Spec,
        val schedule: String,
        val timeZone: String? = null,
        val concurrencyPolicy: ConcurrencyPolicy? = null,
        val startingDeadlineSeconds: Long? = null,
        val suspend: Boolean? = null,
        val successfulJobsHistoryLimit: Int? = null,
        val failedJobsHistoryLimit: Int? = null
    ) : BaseSpec {
        enum class ConcurrencyPolicy {
            Allow,
            Forbid,
            Replace
        }

        class Builder : DSLBuilder<Spec> {
            private var jobTemplate: Job.Spec? = null
            var schedule: String? = null
            var timeZone: String? = null
            var concurrencyPolicy: ConcurrencyPolicy? = null
            var startingDeadlineSeconds: Long? = null
            private var suspend: Boolean? = null
            var successfulJobsHistoryLimit: Int? = null
            var failedJobsHistoryLimit: Int? = null

            fun jobTemplate(block: Job.Spec.Builder.() -> Unit) {
                jobTemplate = Job.Spec.Builder().apply(block).build()
            }

            fun suspend(value: Boolean = true) {
                suspend = value
            }

            override fun build(): Spec {
                return Spec(
                    jobTemplate = vRequireNotNull(this::jobTemplate),
                    schedule = vRequireNotNull(this::schedule),
                    timeZone = timeZone,
                    concurrencyPolicy = concurrencyPolicy,
                    startingDeadlineSeconds = startingDeadlineSeconds,
                    suspend = suspend,
                    successfulJobsHistoryLimit = successfulJobsHistoryLimit,
                    failedJobsHistoryLimit = failedJobsHistoryLimit
                )
            }
        }
    }

    data class Status(
        val active: List<ObjectReference>? = null,
        val lastScheduleTime: LocalDateTime? = null,
        val lastSuccessfulTime: LocalDateTime? = null
    ) : BaseStatus {
        class Builder : DSLBuilder<Status> {
            private var active: List<ObjectReference>? = null
            var lastScheduleTime: LocalDateTime? = null
            var lastSuccessfulTime: LocalDateTime? = null

            fun active(block: ObjectReference.Group.() -> Unit) {
                active = ObjectReference.Group().apply(block).references()
            }

            override fun build(): Status {
                return Status(
                    active = active,
                    lastScheduleTime = lastScheduleTime,
                    lastSuccessfulTime = lastSuccessfulTime
                )
            }
        }
    }

    class Builder : ResourceSpecStatusDSLBuilder<
        CronJob,
        Spec,
        Spec.Builder,
        Status,
        Status.Builder>(Spec.Builder(), Status.Builder()) {

        override fun build(): CronJob {
            return CronJob(
                metadata = metadata,
                spec = spec,
                status = status
            )
        }
    }

    class Group : K8sListResource.ItemGroup<CronJob, Builder>(Builder()) {
        fun cronJobItem(scope: Builder.() -> Unit) {
            item(scope)
        }
    }
}