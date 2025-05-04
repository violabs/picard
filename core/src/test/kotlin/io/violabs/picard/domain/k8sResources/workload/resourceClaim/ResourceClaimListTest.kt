package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimListTest : FullBuildSim<ResourceClaimList, ResourceClaimList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaimList, ResourceClaimList.Builder> {
            scenario {
                id = "minimum"
                given(ResourceClaimList.Builder()) {
                    items {
                        claim {
                            spec {}
                        }
                    }
                }
                expected = ResourceClaimList(
                    items = listOf(ResourceClaim(spec = ResourceClaim.Spec()))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceClaimList, ResourceClaimList.Builder> {
            requireNotEmptyScenario("items") {
                given(ResourceClaimList.Builder())
            }
        }
    }
}