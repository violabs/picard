package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressListTest : FullBuildSim<IPAddressList, IPAddressList.Builder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IPAddressList, IPAddressList.Builder> {
            scenario {
                id = "minimum"
                given(IPAddressList.Builder()) {
                    items {
                        address {  }
                    }
                }
                expected = IPAddressList(
                    items = listOf(IPAddress())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IPAddressList, IPAddressList.Builder> {
            requireNotEmptyScenario("items") {
                given(IPAddressList.Builder())
            }
        }
    }
}