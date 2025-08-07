package io.violabs.picard.v2.resources.storage.csi.driver


import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiDriverTest : SuccessBuildSim<CsiDriver, CsiDriverV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiDriverTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<CsiDriver, CsiDriverV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CsiDriverV2DslBuilder())
                expected = CsiDriver()
            }

            scenario {
                id = "full"
                given(CsiDriverV2DslBuilder()) {
                    metadata {
                        sharedObjectMeta()
                    }

                    spec {
                        attachRequired()
                        podInfoOnMount()
                        requiresRepublish()
                        seLinuxMount()
                        storageCapacity()
                        fsGroupPolicy = PLACEHOLDER
                        tokenRequests {
                            tokenRequest {
                                audience = PLACEHOLDER
                                expirationSeconds = 1
                            }
                        }
                        volumeLifecycleModes(PLACEHOLDER)
                    }
                }
                expected = CsiDriver(
                    metadata = Common.OBJECT_META,
                    spec = CsiDriverSpec(
                        attachRequired = true,
                        podInfoOnMount = true,
                        requiresRepublish = true,
                        seLinuxMount = true,
                        storageCapacity = true,
                        fsGroupPolicy = PLACEHOLDER,
                        tokenRequests = listOf(
                            TokenRequest(
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