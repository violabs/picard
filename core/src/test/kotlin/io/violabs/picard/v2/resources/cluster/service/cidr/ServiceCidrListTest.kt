package io.violabs.picard.v2.resources.cluster.service.cidr

import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class ServiceCidrListTest : FullBuildSim<ServiceCidrList, ServiceCidrListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            ServiceCidrListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<ServiceCidrList, ServiceCidrListDslBuilder> {
            scenario {
                id = "minimum"
                given(ServiceCidrListDslBuilder()) {
                    items {
                        serviceCidr { }
                    }
                }
                expected = ServiceCidrList(
                    items = listOf(
                        ServiceCidr()
                    )
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<ServiceCidrList, ServiceCidrListDslBuilder> {
//            requireNotEmptyScenario("items") {
            requireScenario("items") {
                given(ServiceCidrListDslBuilder())
            }
        }
    }
}