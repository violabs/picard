package io.violabs.picard.v2.resources.service


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceListTest : FullBuildSim<ServiceList, ServiceListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceList, ServiceListDslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceListDslBuilder()) {
                    items {
                        service {
                        }
                    }
                }
                expected = ServiceList(
                    items = listOf(Service())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceList, ServiceListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ServiceListDslBuilder())
            }
        }
    }
}