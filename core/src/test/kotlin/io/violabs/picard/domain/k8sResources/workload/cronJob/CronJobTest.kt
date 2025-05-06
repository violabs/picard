package io.violabs.picard.domain.k8sResources.workload.cronJob


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.domain.k8sResources.workload.job.Job
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CronJobTest : SuccessBuildSim<CronJob, CronJob.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CronJobTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<CronJob, CronJob.Builder> {
            scenario {
                id = "minimum"
                given(CronJob.Builder())
                expected = CronJob()
            }

            scenario {
                id = "full - with minimum job template"
                given(CronJob.Builder()) {
                    sharedMetadata()
                    spec {
                        jobTemplate {  }
                        schedule = PLACEHOLDER
                        timeZone = PLACEHOLDER
                        concurrencyPolicy = CronJob.Spec.ConcurrencyPolicy.Allow
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
                    spec = CronJob.Spec(
                        jobTemplate = Job.Spec(),
                        schedule = PLACEHOLDER,
                        timeZone = PLACEHOLDER,
                        concurrencyPolicy = CronJob.Spec.ConcurrencyPolicy.Allow,
                        startingDeadlineSeconds = 1,
                        suspend = true,
                        successfulJobsHistoryLimit = 1,
                        failedJobsHistoryLimit = 1
                    ),
                    status = CronJob.Status(
                        active = listOf(OBJECT_REFERENCE),
                        lastScheduleTime = NOW,
                        lastSuccessfulTime = NOW
                    )
                )
            }
        }
    }
}