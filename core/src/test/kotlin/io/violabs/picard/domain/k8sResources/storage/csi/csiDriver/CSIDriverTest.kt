package io.violabs.picard.domain.k8sResources.storage.csi.csiDriver


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CSIDriverTest : SuccessBuildSim<CSIDriver, CSIDriver.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<CSIDriver, CSIDriver.Builder> {
            scenario {
                id = "minimum"
                given(CSIDriver.Builder())
                expected = CSIDriver()
            }

            scenario {
                id = "full"
                given(CSIDriver.Builder()) {
                    sharedMetadata()
                    spec {
                        attachRequired()
                        podInfoOnMount()
                        requiresRepublish()
                        seLinuxMount()
                        storageCapacity = 1
                        fsGroupPolicy = PLACEHOLDER
                        tokenRequests {
                            addCSIDriverTokenRequest {
                                audience = PLACEHOLDER
                                expirationSeconds = 1
                            }
                        }
                        volumeLifecycleModes(PLACEHOLDER)
                    }
                }
                expected = CSIDriver(
                    metadata = METADATA,
                    spec = CSIDriver.Spec(
                        attachRequired = true,
                        podInfoOnMount = true,
                        requiresRepublish = true,
                        seLinuxMount = true,
                        storageCapacity = 1,
                        fsGroupPolicy = PLACEHOLDER,
                        tokenRequests = listOf(
                            CSIDriverTokenRequest(
                                audience = PLACEHOLDER,
                                expirationSeconds = 1
                            )
                        ),
                        volumeLifecycleModes = PLACEHOLDER_LIST
                    )
                )
            }
        }
    }
}