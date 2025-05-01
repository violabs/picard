package io.violabs.picard.domain.k8sResources.service.endpointSlice


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointSliceListTest : FullBuildSim<EndpointSliceList, EndpointSliceList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointSliceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointSliceList, EndpointSliceList.Builder> {
            scenario {
                id = "minimum"
                given(EndpointSliceList.Builder()) {
                    items {
                        slice {  }
                    }
                }
                expected = EndpointSliceList(items = listOf(EndpointSlice()))
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<EndpointSliceList, EndpointSliceList.Builder> {
            requireNotEmptyScenario("items") {
                given(EndpointSliceList.Builder())
            }
        }
    }
}