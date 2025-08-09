package io.violabs.picard.v2.resources.workload.pod

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TopologySpreadConstraintTest : FailureBuildSim<TopologySpreadConstraint, TopologySpreadConstraintDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TopologySpreadConstraintTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES =
            possibilities<TopologySpreadConstraint, TopologySpreadConstraintDslBuilder> {
                requireScenario("maxSkew") {
                    given(TopologySpreadConstraintDslBuilder())
                }

                requireScenario("topologyKey") {
                    given(TopologySpreadConstraintDslBuilder()) {
                        maxSkew = 1
                    }
                }

                requireScenario("whenUnsatisfiable") {
                    given(TopologySpreadConstraintDslBuilder()) {
                        maxSkew = 1
                        topologyKey = "fill"
                    }
                }
            }
    }
}