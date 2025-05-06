package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverSpecTest : FailureBuildSim<CSIDriver.Spec, CSIDriver.Spec.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CSIDriver.Spec, CSIDriver.Spec.Builder> {
            requireScenario("attachRequired") {
                given(CSIDriver.Spec.Builder())
            }

            requireScenario("podInfoOnMount") {
                given(CSIDriver.Spec.Builder()) {
                    attachRequired()
                }
            }

            requireScenario("requiresRepublish") {
                given(CSIDriver.Spec.Builder()) {
                    attachRequired()
                    podInfoOnMount()
                }
            }

            requireScenario("seLinuxMount") {
                given(CSIDriver.Spec.Builder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                }
            }

            requireScenario("storageCapacity") {
                given(CSIDriver.Spec.Builder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                }
            }

            requireScenario("fsGroupPolicy") {
                given(CSIDriver.Spec.Builder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity = 1
                }
            }

            requireScenario("tokenRequests") {
                given(CSIDriver.Spec.Builder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity = 1
                    fsGroupPolicy = PLACEHOLDER
                }
            }

            requireNotEmptyScenario("volumeLifecycleModes") {
                given(CSIDriver.Spec.Builder()) {
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