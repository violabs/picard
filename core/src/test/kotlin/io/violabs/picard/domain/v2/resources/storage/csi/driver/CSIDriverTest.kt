package io.violabs.picard.domain.v2.resources.storage.csi.driver

import io.violabs.picard.Common
import io.violabs.picard.Common.sharedObjectMeta
import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverSpec
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverV2
import io.violabs.picard.v2.resources.storage.csi.driver.CsiDriverV2DslBuilder
import io.violabs.picard.v2.resources.storage.csi.driver.TokenRequest
import org.junit.jupiter.api.BeforeAll

class CSIDriverTest : SuccessBuildSim<CsiDriverV2, CsiDriverV2DslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CSIDriverTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<CsiDriverV2, CsiDriverV2DslBuilder> {
            scenario {
                id = "minimum"
                given(CsiDriverV2DslBuilder())
                expected = CsiDriverV2()
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
                expected = CsiDriverV2(
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