package io.violabs.picard.domain.k8sResources.cluster.serviceCIDR


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceCIDRListTest : FullBuildSim<ServiceCIDRList, ServiceCIDRListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceCIDRListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceCIDRList, ServiceCIDRListDslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceCIDRListDslBuilder()) {
                    items {
                        serviceCIDRItem {  }
                    }
                }
                expected = ServiceCIDRList(
                    items = listOf(
                        ServiceCIDR()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceCIDRList, ServiceCIDRListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(ServiceCIDRListDslBuilder())
            }
        }
    }
}