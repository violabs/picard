package io.violabs.picard.domain.k8sResources.cluster.ipAddress


import io.violabs.picard.SuccessBuildSim
import io.violabs.picard.possibilities
import org.junit.jupiter.api.BeforeAll

class IPAddressTest : SuccessBuildSim<IPAddress, IPAddressDslBuilder>() {
    companion object {
        @JvmStatic
        @BeforeAll
        fun setup() = buildSetup(
            IPAddressTest::class,
            SUCCESS_POSSIBILITIES
        )


        private val SUCCESS_POSSIBILITIES = possibilities<IPAddress, IPAddressDslBuilder> {
            scenario {
                id = "minimum"
                given(IPAddressDslBuilder()) {

                }
                expected = IPAddress()
            }
        }
    }
}