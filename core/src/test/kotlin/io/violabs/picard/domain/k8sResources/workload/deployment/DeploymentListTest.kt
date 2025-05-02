package io.violabs.picard.domain.k8sResources.workload.deployment


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeploymentListTest : FullBuildSim<DeploymentList, DeploymentList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeploymentListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeploymentList, DeploymentList.Builder> {
            scenario {
                id = "minimum"
                given(DeploymentList.Builder()) {
                    items {
                        deployment {  }
                    }
                }
                expected = DeploymentList(
                    items = listOf(Deployment())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DeploymentList, DeploymentList.Builder> {
            requireNotEmptyScenario("items") {
                given(DeploymentList.Builder())
            }
        }
    }
}