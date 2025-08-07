package io.violabs.picard.domain.k8sResources.workload.resourceClaim


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceClaimListTest : FullBuildSim<ResourceClaimList, ResourceClaimListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceClaimListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceClaimList, ResourceClaimListDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceClaimListDslBuilder()) {
                    items {
                        resourceClaimItem {
                            spec {}
                        }
                    }
                }
                expected = ResourceClaimList(
                    items = listOf(ResourceClaim(spec = ResourceClaimSpec()))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceClaimList, ResourceClaimListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ResourceClaimListDslBuilder())
            }
        }
    }
}