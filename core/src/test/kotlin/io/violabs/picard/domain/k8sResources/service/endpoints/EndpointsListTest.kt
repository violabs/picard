package io.violabs.picard.domain.k8sResources.service.endpoints


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointsListTest : FullBuildSim<EndpointsList, EndpointsList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointsListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointsList, EndpointsList.Builder> {
            scenario {
                id = "minimum"
                given(EndpointsList.Builder()) {
                    items {
                        endpointsItem {  }
                    }
                }
                expected = EndpointsList(
                    items = listOf(Endpoints())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<EndpointsList, EndpointsList.Builder> {
            requireNotEmptyScenario("items") {
                given(EndpointsList.Builder())
            }
        }
    }
}