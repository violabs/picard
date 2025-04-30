package io.violabs.picard.domain.k8sResources.storage.csi.csiNode


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSINodeSpecTest : FailureBuildSim<CSINode.Spec, CSINode.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSINodeSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSINode.Spec, CSINode.Spec.Builder> {
            requireNotEmptyScenario("drivers") {
                given(CSINode.Spec.Builder())
            }
        }
    }
}