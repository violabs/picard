package io.violabs.picard.v2.resources.workload.batch.cron

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.Common.sharedObjectReference
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.workload.batch.job.JobSpec
import io.violabs.picard.v2.resources.workload.batch.job.JobTemplateSpec
import io.violabs.picard.v2.resources.workload.pod.template.PodTemplateSpec
import org.junit.jupiter.api.BeforeAll

class CronJobTest : SuccessBuildSim<CronJob, CronJobDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CronJobTest::class,
            SUCCESS_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CronJob, CronJobDslBuilder> {
            scenario {
                id = "minimum"
                given(CronJobDslBuilder()) {
                    spec {
                        jobTemplate {
                            spec {
                                template {}
                            }
                        }
                        schedule = PLACEHOLDER
                    }
                }
                expected = CronJob(
                    spec = CronJobSpec(
                        jobTemplate = JobTemplateSpec(
                            spec = JobSpec(
                                template = PodTemplateSpec()
                            )
                        ),
                        schedule = PLACEHOLDER
                    )
                )
            }

            scenario {
                id = "full"
                given(CronJobDslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }
                    spec {
                        jobTemplate {
                            metadata { sharedObjectMeta() }
                            spec {
                                template {  }
                            }
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
                            metadata = Common.OBJECT_META,
                            spec = JobSpec(
                                template = PodTemplateSpec()
                            )
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