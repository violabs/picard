package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceCIDRListTest : FullBuildSim<ServiceCIDRList, ServiceCIDRList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceCIDRListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceCIDRList, ServiceCIDRList.Builder> {
            scenario {
                id = "minimum"
                given(ServiceCIDRList.Builder()) {
                    items {
                        serviceCIDR {  }
                    }
                }
                expected = ServiceCIDRList(
                    items = listOf(
                        ServiceCIDR()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceCIDRList, ServiceCIDRList.Builder> {
            requireNotEmptyScenario("items") {
                given(ServiceCIDRList.Builder())
            }
        }
    }
}