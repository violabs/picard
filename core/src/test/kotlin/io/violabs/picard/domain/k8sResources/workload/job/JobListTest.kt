package io.violabs.picard.domain.k8sResources.workload.job


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class JobListTest : FullBuildSim<JobList, JobList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            JobListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<JobList, JobList.Builder> {
            scenario {
                id = "minimum"
                given(JobList.Builder()) {
                    items {
                        jobItem { }
                    }
                }
                expected = JobList(
                    items = listOf(Job())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<JobList, JobList.Builder> {
            requireNotEmptyScenario("items") {
                given(JobList.Builder())
            }
        }
    }
}