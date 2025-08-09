package io.violabs.picard.v2.resources.workload.set.stateful


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class StatefulSetSpecTest : FailureBuildSim<StatefulSetSpec, StatefulSetSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            StatefulSetSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<StatefulSetSpec, StatefulSetSpecDslBuilder> {
            requireScenario("serviceName") {
                given(StatefulSetSpecDslBuilder())
            }

            requireScenario("selector") {
                given(StatefulSetSpecDslBuilder()) {
                    serviceName = PLACEHOLDER
                }
            }
        }
    }
}