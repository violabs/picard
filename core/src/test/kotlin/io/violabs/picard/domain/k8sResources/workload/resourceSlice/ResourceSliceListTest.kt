package io.violabs.picard.domain.k8sResources.workload.resourceSlice


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ResourceSliceListTest : FullBuildSim<ResourceSliceList, ResourceSliceList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ResourceSliceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ResourceSliceList, ResourceSliceList.Builder> {
            scenario {
                id = "minimum"
                given(ResourceSliceList.Builder()) {
                    items {
                        resourceSliceItem {
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
                        spec = ResourceSlice.Spec(
                            driver = PLACEHOLDER,
                            pool = RESOURCE_POOL
                        )
                    ))
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ResourceSliceList, ResourceSliceList.Builder> {
            requireNotEmptyScenario("items") {
                given(ResourceSliceList.Builder())
            }
        }
    }
}