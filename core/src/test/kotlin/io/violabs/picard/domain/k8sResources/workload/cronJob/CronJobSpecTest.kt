package io.violabs.picard.domain.k8sResources.workload.cronJob


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CronJobSpecTest : FailureBuildSim<CronJob.Spec, CronJob.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CronJobSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CronJob.Spec, CronJob.Spec.Builder> {
            requireScenario("jobTemplate") {
                given(CronJob.Spec.Builder())
            }

            requireScenario("schedule") {
                given(CronJob.Spec.Builder()) {
                    jobTemplate {  }
                }
            }
        }
    }
}