package io.violabs.picard.v2.resources.policy.resource.quota

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceQuotaListTest : FullBuildSim<ResourceQuotaList, ResourceQuotaListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceQuotaListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceQuotaList, ResourceQuotaListDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceQuotaListDslBuilder()) {
                    items {
                        resourceQuota { }
                    }
                }
                expected = ResourceQuotaList(
                    items = listOf(ResourceQuota())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceQuotaList, ResourceQuotaListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ResourceQuotaListDslBuilder())
            }
        }
    }
}