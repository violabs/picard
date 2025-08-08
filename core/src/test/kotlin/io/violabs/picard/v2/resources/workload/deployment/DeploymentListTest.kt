package io.violabs.picard.v2.resources.workload.deployment


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class DeploymentListTest : FullBuildSim<DeploymentList, DeploymentListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            DeploymentListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<DeploymentList, DeploymentListDslBuilder> {
            scenario {
                id = "minimum"
                given(DeploymentListDslBuilder()) {
                    items {
                        deployment {  }
                    }
                }
                expected = DeploymentList(
                    items = listOf(Deployment())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<DeploymentList, DeploymentListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(DeploymentListDslBuilder())
            }
        }
    }
}