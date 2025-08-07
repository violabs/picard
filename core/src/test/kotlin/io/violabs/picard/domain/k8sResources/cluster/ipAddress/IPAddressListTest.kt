package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressListTest : FullBuildSim<IPAddressList, IPAddressListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IPAddressList, IPAddressListDslBuilder> {
            scenario {
                id = "minimum"
                given(IPAddressListDslBuilder()) {
                    items {
                        address {  }
                    }
                }
                expected = IPAddressList(
                    items = listOf(IPAddress())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IPAddressList, IPAddressListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(IPAddressListDslBuilder())
            }
        }
    }
}