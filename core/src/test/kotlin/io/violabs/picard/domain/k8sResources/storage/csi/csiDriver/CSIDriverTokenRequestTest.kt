package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverTokenRequestTest : FailureBuildSim<CSIDriverTokenRequest, CSIDriverTokenRequest.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverTokenRequestTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSIDriverTokenRequest, CSIDriverTokenRequest.Builder> {
            requireScenario("audience") {
                given(CSIDriverTokenRequest.Builder())
            }
        }
    }
}