package io.violabs.picard.domain.k8sResources.service.endpoints


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointsListTest : FullBuildSim<EndpointsList, EndpointsListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointsListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointsList, EndpointsListDslBuilder> {
            scenario {
                id = "minimum"
                given(EndpointsListDslBuilder()) {
                    items {
                        endpointsItem {  }
                    }
                }
                expected = EndpointsList(
                    items = listOf(Endpoints())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<EndpointsList, EndpointsListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(EndpointsListDslBuilder())
            }
        }
    }
}