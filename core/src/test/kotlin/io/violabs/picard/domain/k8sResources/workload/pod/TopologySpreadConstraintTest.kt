package io.violabs.picard.domain.k8sResources.workload.pod


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TopologySpreadConstraintTest : FailureBuildSim<TopologySpreadConstraint, TopologySpreadConstraint.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TopologySpreadConstraintTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<TopologySpreadConstraint, TopologySpreadConstraint.Builder> {
            requireScenario("maxSkew") {
                given(TopologySpreadConstraint.Builder())
            }

            requireScenario("topologyKey") {
                given(TopologySpreadConstraint.Builder()) {
                    maxSkew = 1
                }
            }

            requireScenario("whenUnsatisfiable") {
                given(TopologySpreadConstraint.Builder()) {
                    maxSkew = 1
                    topologyKey = "fill"
                }
            }
        }
    }
}