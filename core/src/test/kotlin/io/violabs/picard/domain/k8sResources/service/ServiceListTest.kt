package io.violabs.picard.domain.k8sResources.service


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceListTest : FullBuildSim<ServiceList, ServiceList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceList, ServiceList.Builder> {
            scenario {
                id = "minimum"
                given(ServiceList.Builder()) {
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

        private val FAILURE_POSSIBILITIES = possibilities<ServiceList, ServiceList.Builder> {
            requireNotEmptyScenario("items") {
                given(ServiceList.Builder())
            }
        }
    }
}