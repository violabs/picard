package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverTokenRequestTest : FailureBuildSim<CSIDriverTokenRequest, CSIDriverTokenRequestDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverTokenRequestTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSIDriverTokenRequest, CSIDriverTokenRequestDslBuilder> {
            requireScenario("audience") {
                given(CSIDriverTokenRequestDslBuilder())
            }
        }
    }
}