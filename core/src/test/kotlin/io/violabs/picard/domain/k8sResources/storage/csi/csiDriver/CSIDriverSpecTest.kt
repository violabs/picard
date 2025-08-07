package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverSpecTest : FailureBuildSim<CSIDriverSpec, CSIDriverSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSIDriverSpec, CSIDriverSpecDslBuilder> {
            requireScenario("attachRequired") {
                given(CSIDriverSpecDslBuilder())
            }

            requireScenario("podInfoOnMount") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                }
            }

            requireScenario("requiresRepublish") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                }
            }

            requireScenario("seLinuxMount") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                }
            }

            requireScenario("storageCapacity") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                }
            }

            requireScenario("fsGroupPolicy") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity = 1
                }
            }

            requireScenario("tokenRequests") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity = 1
                    fsGroupPolicy = PLACEHOLDER
                }
            }

            requireNotEmptyScenario("volumeLifecycleModes") {
                given(CSIDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity = 1
                    fsGroupPolicy = PLACEHOLDER
                    tokenRequests {
                        addCSIDriverTokenRequest {
                            audience = PLACEHOLDER
                        }
                    }
                }
            }
        }
    }
}