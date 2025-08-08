package io.violabs.picard.v2.resources.cluster.ipaddress


import io.violabs.picard.FullBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IpAddressListTest : FullBuildSim<IpAddressList, IpAddressListDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IpAddressListTest::class,
            SUCCESS_POSSIBILITIES,
            FAILURE_POSSIBILITIES
        )

        private val SUCCESS_POSSIBILITIES = possibilities<IpAddressList, IpAddressListDslBuilder> {
            scenario {
                id = "minimum"
                given(IpAddressListDslBuilder()) {
                    items {
                        ipAddress {  }
                    }
                }
                expected = IpAddressList(
                    items = listOf(IpAddress())
                )
            }
        }

        private val FAILURE_POSSIBILITIES = possibilities<IpAddressList, IpAddressListDslBuilder> {
            requireNotEmptyScenario("items") {
                given(IpAddressListDslBuilder())
            }
        }
    }
}