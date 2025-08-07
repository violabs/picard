package io.violabs.picard.domain.k8sResources.workload.cronJob


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CronJobListTest : FullBuildSim<CronJobList, CronJobListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CronJobListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<CronJobList, CronJobListDslBuilder> {
            scenario {
                id = "minimum"
                given(CronJobListDslBuilder()) {
                    items {
                        cronJobItem { }
                    }
                }
                expected = CronJobList(
                    items = listOf(CronJob())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<CronJobList, CronJobListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(CronJobListDslBuilder())
            }
        }
    }
}