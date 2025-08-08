package io.violabs.picard.v2.resources.workload.batch.cron


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