package io.violabs.picard.domain


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class TaintTest : FailureBuildSim<Taint, Taint.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            TaintTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<Taint, Taint.Builder> {
            requireScenario("effect") {
                given(Taint.Builder())
            }
        }
    }
}