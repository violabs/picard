package io.violabs.picard.domain.k8sResources.storage.csi.csiNode


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSINodeSpecTest : FailureBuildSim<CSINodeSpec, CSINodeSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSINodeSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSINodeSpec, CSINodeSpecDslBuilder> {
            requireNotEmptyScenario("drivers") {
                given(CSINodeSpecDslBuilder())
            }
        }
    }
}