package io.violabs.picard.v2.resources.workload.resource.claim


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
                        resourceClaim {
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
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ResourceClaimListDslBuilder())
            }
        }
    }
}