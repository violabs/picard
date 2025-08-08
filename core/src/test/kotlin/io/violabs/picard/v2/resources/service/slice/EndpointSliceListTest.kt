package io.violabs.picard.v2.resources.service.slice

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class EndpointSliceListTest : FullBuildSim<EndpointSliceList, EndpointSliceListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            EndpointSliceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<EndpointSliceList, EndpointSliceListDslBuilder> {
            scenario {
                id = "minimum"
                given(EndpointSliceListDslBuilder()) {
                    items {
                        endpointSlice {
                            addressType = PLACEHOLDER
                            endpoints {
                                endpoint {
                                    addresses(PLACEHOLDER)
                                }
                            }
                        }
                    }
                }
                expected = EndpointSliceList(items = listOf(EndpointSlice(
                    addressType = PLACEHOLDER,
                    endpoints = listOf(Endpoint(
                        addresses = listOf(PLACEHOLDER),
                    )),
                )))
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<EndpointSliceList, EndpointSliceListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(EndpointSliceListDslBuilder())
            }
        }
    }
}