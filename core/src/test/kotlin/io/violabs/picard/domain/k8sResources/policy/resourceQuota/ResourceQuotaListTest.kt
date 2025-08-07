package io.violabs.picard.domain.k8sResources.policy.resourceQuota


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
                        resourceQuotaItem {  }
                    }
                }
                expected = ResourceQuotaList(
                    items = listOf(ResourceQuota())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceQuotaList, ResourceQuotaListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ResourceQuotaListDslBuilder())
            }
        }
    }
}