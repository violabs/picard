package io.violabs.picard.domain.k8sResources.workload.cronJob


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.job.Job
import io.violabs.picard.possibilities
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
                given(CronJobDslBuilder())
                expected = CronJob()
            }

            scenario {
                id = "full - with minimum job template"
                given(CronJobDslBuilder()) {
                    sharedMetadata()
                    spec {
                        jobTemplate {  }
                        schedule = PLACEHOLDER
                        timeZone = PLACEHOLDER
                        concurrencyPolicy = CronJobSpec.ConcurrencyPolicy.Allow
                        startingDeadlineSeconds = 1
                        suspend()
                        successfulJobsHistoryLimit = 1
                        failedJobsHistoryLimit = 1
                    }
                    this.status {
                        active {
                            addObjectReference {
                                sharedObjectReference()
                            }
                        }
                        lastScheduleTime = NOW
                        lastSuccessfulTime = NOW
                    }
                }
                expected = CronJob(
                    metadata = METADATA,
                    spec = CronJobSpec(
                        jobTemplate = JobSpec(),
                        schedule = PLACEHOLDER,
                        timeZone = PLACEHOLDER,
                        concurrencyPolicy = CronJobSpec.ConcurrencyPolicy.Allow,
                        startingDeadlineSeconds = 1,
                        suspend = true,
                        successfulJobsHistoryLimit = 1,
                        failedJobsHistoryLimit = 1
                    ),
                    status = CronJobStatus(
                        active = listOf(OBJECT_REFERENCE),
                        lastScheduleTime = NOW,
                        lastSuccessfulTime = NOW
                    )
                )
            }
        }
    }
}