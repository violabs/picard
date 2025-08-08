package io.violabs.picard.v2.resources.cluster.node

import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TaintTest : FailureBuildSim<Taint, TaintDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TaintTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Taint, TaintDslBuilder> {
            requireScenario("effect") {
                given(TaintDslBuilder())
            }
        }
    }
}