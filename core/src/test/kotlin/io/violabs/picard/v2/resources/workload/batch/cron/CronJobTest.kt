package io.violabs.picard.v2.resources.workload.batch.cron

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.job.Job
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.batch.job.JobTemplateSpec
import org.junit.jupiter.api.BeforeAll

class CronJobTest : SuccessBuildSim<CronJob, CronJobV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CronJobTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CronJob, CronJobV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CronJobV2DslBuilder()) {
                    spec {
                        jobTemplate {
                            spec = Job.Spec()
                        }
                        schedule = PLACEHOLDER
                    }
                }
                expected = CronJob(
                    spec = CronJobSpec(
                        jobTemplate = JobTemplateSpec(
                            spec = Job.Spec()
                        ),
                        schedule = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                given(CronJobV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        jobTemplate {
                            metadata = METADATA
                            spec = Job.Spec()
                        }
                        schedule = PLACEHOLDER
                        timeZone = PLACEHOLDER
                        concurrencyPolicy = PLACEHOLDER
                        startingDeadlineSeconds = 1
                        suspend(true)
                        successfulJobsHistoryLimit = 1
                        failedJobsHistoryLimit = 1
                    }
                    status {
                        active {
                            objectReference {
                                sharedObjectReference()
                            }
                        }
                        lastScheduleTime = NOW
                        lastSuccessfulTime = NOW
                    }
                }
                expected = CronJob(
                    metadata = Common.OBJECT_META,
                    spec = CronJobSpec(
                        jobTemplate = JobTemplateSpec(
                            metadata = METADATA,
                            spec = Job.Spec()
                        ),
                        schedule = PLACEHOLDER,
                        timeZone = PLACEHOLDER,
                        concurrencyPolicy = PLACEHOLDER,
                        startingDeadlineSeconds = 1,
                        suspend = true,
                        successfulJobsHistoryLimit = 1,
                        failedJobsHistoryLimit = 1
                    ),
                    status = CronJobStatus(
                        active = listOf(Common.OBJECT_REFERENCE),
                        lastScheduleTime = NOW,
                        lastSuccessfulTime = NOW
                    )
                )
            }
        }
    }
}