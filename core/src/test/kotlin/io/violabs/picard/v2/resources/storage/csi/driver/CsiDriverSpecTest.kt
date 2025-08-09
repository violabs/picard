package io.violabs.picard.v2.resources.storage.csi.driver


import io.violabs.picard.FailureBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class CsiDriverSpecTest : FailureBuildSim<CsiDriverSpec, CsiDriverSpecDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            CsiDriverSpecTest::class,
            failureScenariosSet = FAILURE_POSSIBILITIES
        )

        private val FAILURE_POSSIBILITIES = possibilities<CsiDriverSpec, CsiDriverSpecDslBuilder> {
            requireScenario("attachRequired") {
                given(CsiDriverSpecDslBuilder())
            }

            requireScenario("podInfoOnMount") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                }
            }

            requireScenario("requiresRepublish") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                }
            }

            requireScenario("seLinuxMount") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                }
            }

            requireScenario("storageCapacity") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                }
            }

            requireScenario("fsGroupPolicy") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity()
                }
            }

            requireScenario("tokenRequests") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity()
                    fsGroupPolicy = PLACEHOLDER
                }
            }

//            requireNotEmptyScenario("volumeLifecycleModes") {
            requireScenario("volumeLifecycleModes") {
                given(CsiDriverSpecDslBuilder()) {
                    attachRequired()
                    podInfoOnMount()
                    requiresRepublish()
                    seLinuxMount()
                    storageCapacity()
                    fsGroupPolicy = PLACEHOLDER
                    tokenRequests {
                        tokenRequest {
                            audience = PLACEHOLDER
                        }
                    }
                }
            }
        }
    }
}