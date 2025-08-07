package io.violabs.picard.domain.k8sResources.workload.cronJob


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CronJobSpecTest : FailureBuildSim<CronJobSpec, CronJobSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CronJobSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CronJobSpec, CronJobSpecDslBuilder> {
            requireScenario("jobTemplate") {
                given(CronJobSpecDslBuilder())
            }

            requireScenario("schedule") {
                given(CronJobSpecDslBuilder()) {
                    jobTemplate {  }
                }
            }
        }
    }
}