package io.violabs.picard.v2.resources.storage.csi.node


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiNodeSpecTest : FailureBuildSim<CsiNodeSpec, CsiNodeSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiNodeSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CsiNodeSpec, CsiNodeSpecDslBuilder> {
//            requireNotEmptyScenario("drivers") {
            requireScenario("drivers") {
                given(CsiNodeSpecDslBuilder())
            }
        }
    }
}