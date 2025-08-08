package io.violabs.picard.v2.resources.workload.resource.slice


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceSliceListTest : FullBuildSim<ResourceSliceList, ResourceSliceListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceSliceList, ResourceSliceListDslBuilder> {
            scenario {
                id = "minimum"
                given(ResourceSliceListDslBuilder()) {
                    items {
                        resourceSlice {
                            spec {
                                driver = PLACEHOLDER
                                pool {
                                    generation = 1
                                    name = PLACEHOLDER
                                    resourceSliceCount = 1
                                }
                            }
                        }
                    }
                }
                expected = ResourceSliceList(
                    items = listOf(ResourceSlice(
                        spec = ResourceSliceSpec(
                            driver = PLACEHOLDER,
                            pool = RESOURCE_POOL
                        )
                    ))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceSliceList, ResourceSliceListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ResourceSliceListDslBuilder())
            }
        }
    }
}